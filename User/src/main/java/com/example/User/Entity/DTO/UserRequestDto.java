package com.example.User.Entity.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username; // ach
}
