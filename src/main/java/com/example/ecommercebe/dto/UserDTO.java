package com.example.ecommercebe.dto;

import com.example.ecommercebe.entities.Role;
import com.example.ecommercebe.entities.User;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String address;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String gender;
    private Set<Role> roles = new HashSet<>();

    public UserDTO(User user) {
    }
}