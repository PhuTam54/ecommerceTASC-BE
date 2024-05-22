package com.example.ecommercebe.dto.response;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String accountName;
    private String address;
    private String email;
    private String phoneNumber;

    public UserDTO() {
    }
}