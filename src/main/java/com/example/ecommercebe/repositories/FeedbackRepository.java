package com.example.ecommercebe.repositories;


import com.example.ecommercebe.entities.Clinic;
import com.example.ecommercebe.entities.Feedback;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>, JpaSpecificationExecutor<Feedback> {

    List<Feedback> findByProduct(Product product);
    List<Feedback> findByClinic(Clinic clinic);
    List<Feedback> findByRating(int rating);
    List<Feedback> findByUser(User user);


}
