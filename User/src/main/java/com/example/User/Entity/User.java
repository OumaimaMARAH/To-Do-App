package com.example.User.Entity;

import com.example.User.Model.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Check if null
    @Column(name = "first_name")
    private String firstName;
    //Check if null
    @Column(name = "last_name")
    private String lastName;
    //Check if null & validate email & unique
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "role")
    private String role;
    @OneToMany()
    @Transient
    private Task tasks;



}
