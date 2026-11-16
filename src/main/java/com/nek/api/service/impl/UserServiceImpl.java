package com.nek.api.service.impl;

import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nek.api.dto.UserFilter;
import com.nek.api.dto.UserRequestDTO;
import com.nek.api.dto.UserResponseDTO;
import com.nek.api.entity.User;
import com.nek.api.exception.DuplicateResourceException;
import com.nek.api.exception.ResourceNotFoundException;
import com.nek.api.repository.UserRepository;
import com.nek.api.repository.UserSpecifications;
import com.nek.api.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        String email = normalizeEmail(request.email());
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException("Usuario", "email", email);
        }
        User user = new User();
        apply(user, request, email);
        return UserResponseDTO.from(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        return UserResponseDTO.from(find(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findUsers(UserFilter filter, Pageable pageable) {
        return userRepository.findAll(UserSpecifications.matching(filter), pageable).map(UserResponseDTO::from);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User user = find(id);
        String email = normalizeEmail(request.email());
        if (userRepository.existsByEmailIgnoreCaseAndIdNot(email, id)) {
            throw new DuplicateResourceException("Usuario", "email", email);
        }
        apply(user, request, email);
        return UserResponseDTO.from(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(find(id));
    }

    @Override
    public UserResponseDTO setActive(Long id, boolean active) {
        User user = find(id);
        user.setActive(active);
        return UserResponseDTO.from(userRepository.save(user));
    }

    private User find(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
    }

    private static void apply(User user, UserRequestDTO request, String email) {
        user.setFirstName(request.firstName().trim());
        user.setLastName(request.lastName().trim());
        user.setEmail(email);
        user.setPhone(request.phone() == null || request.phone().isBlank() ? null : request.phone().trim());
    }

    /** El email se guarda en minúsculas y sin espacios: evita duplicados como Ana@x.com y ana@x.com. */
    private static String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
