package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findUserByEmails(Set<String> emails) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        Path<String> emailPath = user.get("email");

        List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.like(emailPath, email));
        }
        query.select(user)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}