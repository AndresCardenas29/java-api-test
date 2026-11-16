# Users API · Spring Boot 3

API REST de usuarios hecha como referencia de cómo construyo un servicio backend en Java: capas limpias, validación, paginación con filtros dinámicos, errores estándar, documentación interactiva, pruebas y despliegue automatizado.

**Stack:** Java 21 · Spring Boot 3.5 · Spring Data JPA · PostgreSQL / H2 · springdoc-openapi · JUnit 5 · Mockito · Docker · GitHub Actions · Dokploy

## Qué muestra este proyecto

| Tema | Cómo está resuelto |
| --- | --- |
| Arquitectura en capas | `controller` → `service` (interfaz + implementación) → `repository` → `entity`, con DTOs como `record` de Java 21. |
| Paginación y filtros | `GET /api/users?search=&active=&page=&size=&sort=` con `Specification` de JPA: los filtros se combinan solo si vienen. Tamaño máximo de página: 100. |
| Validación | Bean Validation en el DTO de entrada; el email se normaliza (minúsculas, sin espacios) para evitar duplicados. |
| Errores | Formato estándar **RFC 9457** (`application/problem+json`) con detalle por campo. Los errores inesperados nunca exponen mensajes internos. |
| Documentación | Swagger UI en **`/docs`** y OpenAPI en `/v3/api-docs`. La raíz `/` redirige a la documentación. |
| Observabilidad | `GET /actuator/health` y `HEALTHCHECK` en la imagen Docker. |
| Pruebas | Unitarias del servicio (Mockito) y de punta a punta con MockMvc + H2: creación, validación, duplicados, paginación, filtros, ciclo completo y documentación. |
| CI/CD | GitHub Actions compila y prueba cada push y PR; solo si pasan, despliega en Dokploy (`main` → producción, `dev` → desarrollo). |
| Configuración | Perfiles `dev`, `qa` y `prod`; credenciales por variables de entorno (`.env.template`). |

## Endpoints

| Método | Ruta | Descripción |
| --- | --- | --- |
| `POST` | `/api/users` | Crea un usuario. Responde `201` con cabecera `Location`. |
| `GET` | `/api/users` | Lista paginada. Filtros: `search` (nombre, apellido o email), `active`. Orden: `sort=campo,asc\|desc`. |
| `GET` | `/api/users/{id}` | Obtiene un usuario. |
| `PUT` | `/api/users/{id}` | Actualiza un usuario. |
| `DELETE` | `/api/users/{id}` | Elimina un usuario (`204`). |
| `PATCH` | `/api/users/{id}/activate` | Activa un usuario. |
| `PATCH` | `/api/users/{id}/deactivate` | Desactiva un usuario. |

Ejemplo de lista:

```http
GET /api/users?search=gomez&active=true&page=0&size=10&sort=lastName,asc
```

```json
{
  "content": [
    { "id": 1, "firstName": "Ana", "lastName": "Gómez", "email": "ana@example.com", "phone": "+573001234567", "active": true,
      "createdAt": "2026-11-16T10:00:00", "updatedAt": "2026-11-16T10:00:00" }
  ],
  "page": 0, "size": 10, "totalElements": 1, "totalPages": 1, "first": true, "last": true
}
```

Ejemplo de error de validación:

```json
{
  "type": "about:blank",
  "title": "Datos inválidos",
  "status": 400,
  "detail": "Revisa los campos indicados en 'errors'.",
  "instance": "/api/users",
  "errors": { "email": "El email debe ser válido" },
  "timestamp": "2026-11-16T15:00:00Z"
}
```

## Ejecutar

**Sin instalar nada más que Java 21** (usa H2 en memoria):

```bash
./mvnw spring-boot:run
```

**Con PostgreSQL usando Docker:**

```bash
docker compose up --build
```

Luego abre http://localhost:8080/docs.

**Pruebas:**

```bash
./mvnw verify
```

## Configuración

Copia `.env.template` a `.env` y ajusta los valores:

| Variable | Uso |
| --- | --- |
| `APP_PROFILE` | `dev` (H2 por defecto, crea tablas), `qa` o `prod` (validan el esquema). |
| `DB_URL`, `DB_DRIVER`, `DB_USERNAME`, `DB_PASSWORD` | Conexión a la base de datos. |
| `SERVER_PORT` | Puerto HTTP (por defecto `8080`). |

## Despliegue

El workflow [`.github/workflows/deploy.yml`](.github/workflows/deploy.yml) despliega en Dokploy después de que las pruebas pasan. Las URLs de los webhooks se guardan como secretos del repositorio:

- `DOKPLOY_WEBHOOK_PROD` → rama `main`
- `DOKPLOY_WEBHOOK_DEV` → rama `dev`

## Estructura

```
src/main/java/com/nek/api
├── config/         OpenAPI
├── controller/     REST (UserController) y redirección de la raíz
├── dto/            Records de entrada/salida, filtros y página
├── entity/         Entidad JPA
├── exception/      Excepciones de dominio y manejador RFC 9457
├── repository/     Spring Data JPA + Specifications
└── service/        Lógica de negocio
```

---

Hecho por [Andrés Cárdenas](https://nekdress.com).
