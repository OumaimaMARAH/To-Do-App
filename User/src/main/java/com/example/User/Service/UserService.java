package com.example.User.Service;

import com.example.User.Entity.DTO.UserRequestDto;
import com.example.User.Entity.DTO.UserResponseDto;
import com.example.User.Entity.User;
import com.example.User.Exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto createUser(UserRequestDto userDto);
    public UserResponseDto addUser(UserRequestDto userDto);
    UserResponseDto updateUser(Long id, UserRequestDto userDto) throws UserNotFoundException;
    void deleteUser(Long id) throws Exception;
    Object getUserById(Long id) throws Exception;
    void DeleteUser(Long userId);
    User getUserByEmail(String email);


}
