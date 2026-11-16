package com.nek.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nek.api.dto.UserFilter;
import com.nek.api.dto.UserRequestDTO;
import com.nek.api.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);

    UserResponseDTO getUserById(Long id);

    Page<UserResponseDTO> findUsers(UserFilter filter, Pageable pageable);

    UserResponseDTO updateUser(Long id, UserRequestDTO request);

    void deleteUser(Long id);

    UserResponseDTO setActive(Long id, boolean active);
}
