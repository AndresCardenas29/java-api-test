package com.nek.api.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jayway.jsonpath.JsonPath;
import com.nek.api.repository.UserRepository;

/** Pruebas de punta a punta contra H2 en memoria: HTTP → controlador → servicio → JPA. */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void clean() {
        repository.deleteAll();
    }

    private long create(String first, String last, String email) throws Exception {
        String body = """
                {"firstName":"%s","lastName":"%s","email":"%s","phone":"+573001234567"}
                """.formatted(first, last, email);
        String json = mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(json, "$.id")).longValue();
    }

    @Test
    void createReturnsLocationAndNormalizedEmail() throws Exception {
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName":"Ana","lastName":"Gómez","email":"ANA@Example.com"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/users/")))
                .andExpect(jsonPath("$.email").value("ana@example.com"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void invalidBodyReturnsProblemWithFieldErrors() throws Exception {
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName":"A","lastName":"","email":"no-es-email"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title").value("Datos inválidos"))
                .andExpect(jsonPath("$.errors.firstName").exists())
                .andExpect(jsonPath("$.errors.lastName").exists())
                .andExpect(jsonPath("$.errors.email").exists());
    }

    @Test
    void duplicateEmailReturnsConflict() throws Exception {
        create("Ana", "Gómez", "ana@example.com");
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName":"Otra","lastName":"Ana","email":"Ana@Example.com"}
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409));
    }

    @Test
    void listIsPaginatedAndFiltered() throws Exception {
        create("Ana", "Gómez", "ana@example.com");
        create("Luis", "Pérez", "luis@example.com");
        long carla = create("Carla", "Ruiz", "carla@example.com");
        mvc.perform(patch("/api/users/{id}/deactivate", carla)).andExpect(status().isOk());

        mvc.perform(get("/api/users").param("size", "2").param("sort", "firstName,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].firstName").value("Ana"))
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.totalPages").value(2));

        mvc.perform(get("/api/users").param("search", "pérez"))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].firstName").value("Luis"));

        mvc.perform(get("/api/users").param("active", "false"))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].firstName").value("Carla"));
    }

    @Test
    void invalidSortFieldReturnsBadRequest() throws Exception {
        mvc.perform(get("/api/users").param("sort", "noExiste,asc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Orden inválido"));
    }

    @Test
    void updateGetAndDeleteLifecycle() throws Exception {
        long id = create("Ana", "Gómez", "ana@example.com");

        mvc.perform(put("/api/users/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName":"Ana María","lastName":"Gómez","email":"ana@example.com"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ana María"));

        mvc.perform(get("/api/users/{id}", id)).andExpect(jsonPath("$.firstName").value("Ana María"));
        mvc.perform(delete("/api/users/{id}", id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/users/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON));
    }

    @Test
    void rootRedirectsToDocsAndDocsAreServed() throws Exception {
        mvc.perform(get("/")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/docs"));
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.title").value("Users API"))
                .andExpect(jsonPath("$.paths['/api/users']").exists());
    }

    @Test
    void healthIsUp() throws Exception {
        mvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void unknownRouteReturnsProblem() throws Exception {
        mvc.perform(get("/api/nada"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.detail", endsWith("api/nada")));
    }
}
