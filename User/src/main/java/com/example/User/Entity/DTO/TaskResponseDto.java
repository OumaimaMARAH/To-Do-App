package com.example.User.Entity.DTO;


import com.example.User.Entity.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private Status status;
    private Date dueDate;
    private Date updatedAt;
}
