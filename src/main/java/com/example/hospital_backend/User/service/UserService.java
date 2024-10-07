package com.example.hospital_backend.User.service;

import com.example.hospital_backend.User.dto.UserDTO;
import com.example.hospital_backend.User.entity.User;
import com.example.hospital_backend.User.mapper.UserMapper;
import com.example.hospital_backend.User.repository.UserRepository;
import com.example.hospital_backend.exception.ResourceNotFoundException;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
            .map(userMapper::toUserDTO) // 엔티티 -> DTO
            .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id)
            .map(userMapper::toUserDTO)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new DuplicateFormatFlagsException(
                "Username already exists: " + userDTO.getUsername());
        }
        User user = userMapper.toUser(userDTO); // DTO -> 엔티티
        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser); // 엔티티 -> DTO
    }

}
