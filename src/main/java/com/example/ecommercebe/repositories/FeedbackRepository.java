package com.example.ecommercebe.repositories;


import com.example.ecommercebe.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>, JpaSpecificationExecutor<Feedback> {

    List<Feedback> findByProductId(long productId);
    List<Feedback> findByClinicId(long clinicId);
    List<Feedback> findByRating(int rating);
    List<Feedback> findByUserId(long rating);


}
