package com.example.Task.Model;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
public class User {
    private Long id;
    private String fullName;
    private String email;

}
