package com.example.ecommercebe.controller;

import com.example.ecommercebe.entities.User;
import com.example.ecommercebe.models.requests.UserRequest;
import com.example.ecommercebe.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Tag(name = "User", description = "User Controller")
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit) {
        return ResponseEntity.ok(userService.findAll(PageRequest.of(page - 1, limit)));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/username")
    public ResponseEntity<User> getUserByUsername(@RequestParam(name = "username") String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User newUser = userService.createUser(userRequest);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserRequest userRequest) {
        User updatedUser = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
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