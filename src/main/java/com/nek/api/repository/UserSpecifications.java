package com.nek.api.repository;

import java.util.Locale;

import org.springframework.data.jpa.domain.Specification;

import com.nek.api.dto.UserFilter;
import com.nek.api.entity.User;

/** Construye la consulta dinámica a partir de los filtros recibidos. */
public final class UserSpecifications {

    private UserSpecifications() {
    }

    public static Specification<User> matching(UserFilter filter) {
        Specification<User> spec = (root, query, cb) -> cb.conjunction();
        if (filter == null) {
            return spec;
        }
        if (filter.active() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("active"), filter.active()));
        }
        if (filter.search() != null && !filter.search().isBlank()) {
            String like = "%" + filter.search().trim().toLowerCase(Locale.ROOT) + "%";
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("firstName")), like),
                    cb.like(cb.lower(root.get("lastName")), like),
                    cb.like(cb.lower(root.get("email")), like)));
        }
        return spec;
    }
}
