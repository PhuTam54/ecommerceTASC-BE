package com.example.ecommercebe.obj;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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