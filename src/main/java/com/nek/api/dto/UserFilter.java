package com.nek.api.dto;

/**
 * Filtros opcionales para listar usuarios.
 *
 * @param search texto a buscar en nombre, apellido o email
 * @param active si se indica, filtra por estado
 */
public record UserFilter(String search, Boolean active) {
}
