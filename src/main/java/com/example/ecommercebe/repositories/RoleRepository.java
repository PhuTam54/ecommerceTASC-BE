package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.Role;
import com.example.ecommercebe.statics.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
