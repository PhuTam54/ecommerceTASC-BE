package com.example.ecommercebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String accountName;
    private String address;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Order> orders;
    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private ShoppingCart shoppingCart;

    public User(String userName, String password, String accountName, String address, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.accountName = accountName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accountName='" + accountName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
