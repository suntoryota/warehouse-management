package com.example.demo.entity;

import com.example.demo.constant.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}