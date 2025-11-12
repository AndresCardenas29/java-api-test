package com.nek.api.service;

import java.util.List;

import com.nek.api.dto.UserRequestDTO;
import com.nek.api.dto.UserResponseDTO;

public interface UserService {
    
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    
    UserResponseDTO getUserById(Long id);
    
    List<UserResponseDTO> getAllUsers();
    
    List<UserResponseDTO> getActiveUsers();
    
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    
    void deleteUser(Long id);
    
    void deactivateUser(Long id);
    
    void activateUser(Long id);
    
    List<UserResponseDTO> searchUsers(String searchTerm);
}
