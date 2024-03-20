package com.example.User.Model;

import lombok.Data;

import java.util.Date;

@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private String status;
    private Date dueDate;
    private Date updatedAt;

}
