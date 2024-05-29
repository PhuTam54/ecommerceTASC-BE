package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.UserDTO;
import com.example.ecommercebe.models.requests.UserRequest;
import com.example.ecommercebe.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(userService.getAll(PageRequest.of(page - 1, limit)));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/trash")
    public ResponseEntity<?> getInTrashUsers(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit) {
        return ResponseEntity.ok(userService.getInTrash(PageRequest.of(page - 1, limit)));
    }

    @GetMapping(path = "/username")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam(name = "username") String username) {
        UserDTO user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequest userRequest) {
        UserDTO newUser = userService.createUser(userRequest);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserRequest userRequest) {
        UserDTO updatedUser = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        userService.moveToTrash(id);
        return ResponseEntity.ok().build();
    }
}