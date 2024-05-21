package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.User;

import java.util.List;
import java.util.Set;

public interface UserRepositoryCustom {
    List<User> findUserByEmails(Set<String> emails);
}