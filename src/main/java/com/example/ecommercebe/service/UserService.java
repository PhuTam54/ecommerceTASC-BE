package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.User;
import com.example.ecommercebe.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

//    public Collection<User> findByName(String name) {
//        return userRepository.findByName(name);
//    }
//
//    public Collection<User> findByNameNative(String name) {
//        return userRepository.findByNameNative(name);
//    }
//
//    public List<User> findUserByEmails(Set<String> emails) {
//        return userRepository.findUserByEmails(emails);
//    }

//    public Collection<UserDTO> getUserAndCart() {
//        return userRepository.getUserAndCart();
//    }
//
//    public List<UserProjection> getUserAndCartProjection() {
//        return userRepository.getUserAndCartProjection();
//    }
//
//    public List<ResultDTO> findResultDTOByCustomer(int id) {
//        return userRepository.findResultDTOByCustomer(id);
//    }
}
