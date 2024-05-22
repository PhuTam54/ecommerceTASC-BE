package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.User;

import java.util.List;
import java.util.Set;

public interface UserRepositoryCustom {
    List<User> findUserByEmails(Set<String> emails);
}