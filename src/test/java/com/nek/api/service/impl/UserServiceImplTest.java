package com.nek.api.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nek.api.dto.UserRequestDTO;
import com.nek.api.entity.User;
import com.nek.api.exception.DuplicateResourceException;
import com.nek.api.exception.ResourceNotFoundException;
import com.nek.api.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    private static UserRequestDTO request(String email) {
        return new UserRequestDTO("  Ana ", "Gómez", email, " ");
    }

    @Test
    void createNormalizesEmailAndTrimsFields() {
        when(repository.existsByEmailIgnoreCase("ana@example.com")).thenReturn(false);
        when(repository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        service.createUser(request("  Ana@Example.COM "));

        ArgumentCaptor<User> saved = ArgumentCaptor.forClass(User.class);
        verify(repository).save(saved.capture());
        assertThat(saved.getValue().getEmail()).isEqualTo("ana@example.com");
        assertThat(saved.getValue().getFirstName()).isEqualTo("Ana");
        assertThat(saved.getValue().getPhone()).isNull();
    }

    @Test
    void createRejectsDuplicateEmail() {
        when(repository.existsByEmailIgnoreCase("ana@example.com")).thenReturn(true);

        assertThatThrownBy(() -> service.createUser(request("ana@example.com")))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("ana@example.com");
        verify(repository, never()).save(any());
    }

    @Test
    void updateRejectsEmailUsedByAnotherUser() {
        User existing = new User("Ana", "Gómez", "ana@example.com", null);
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.existsByEmailIgnoreCaseAndIdNot("otro@example.com", 1L)).thenReturn(true);

        assertThatThrownBy(() -> service.updateUser(1L, request("otro@example.com")))
                .isInstanceOf(DuplicateResourceException.class);
    }

    @Test
    void missingUserThrowsNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getUserById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void setActiveChangesState() {
        User user = new User("Ana", "Gómez", "ana@example.com", null);
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);

        assertThat(service.setActive(1L, false).active()).isFalse();
    }
}
