package com.example.ecommercebe.controller;

import com.example.ecommercebe.entities.User;
import com.example.ecommercebe.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

//    @GetMapping(path = "/name")
//    public ResponseEntity<Collection<User>> getUsersByName(@RequestParam(name = "name") String name) {
//        Collection<User> users = userService.findByName(name);
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping(path = "/name/native")
//    public ResponseEntity<Collection<User>> getUsersByNameNative(@RequestParam(name = "name") String name) {
//        Collection<User> users = userService.findByNameNative(name);
//        return ResponseEntity.ok(users);
//    }

//    @GetMapping(path = "/user-cart")
//    public ResponseEntity<List<UserDTO>> getUserAndCart() {
//        List<UserDTO> users = userService.getUserAndCart();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping(path = "/user-cart/projection")
//    public ResponseEntity<List<UserProjection>> getUserAndCartProjection() {
//        List<UserProjection> users = userService.getUserAndCartProjection();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping(path = "/result-dto")
//    public ResponseEntity<List<ResultDTO>> getResultDTOByCustomer(@RequestParam(name = "id") int id) {
//        List<ResultDTO> users = userService.findResultDTOByCustomer(id);
//        return ResponseEntity.ok(users);
//    }
}