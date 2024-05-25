package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.FeedbackDTO;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbackByProductId(@PathVariable long productId) {
        List<FeedbackDTO> feedbackDTO = feedbackService.getAllFeedbackByUserId(productId);
        if (feedbackDTO == null) {
            throw new CategoryNotFoundException("Feedback not found with product_Id: " + productId);
        }
        return ResponseEntity.ok(feedbackDTO);
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbackByClinicId(@PathVariable long clinicId) {
        List<FeedbackDTO> feedbackDTO = feedbackService.getAllFeedbackByUserId(clinicId);
        if (feedbackDTO == null) {
            throw new CategoryNotFoundException("Feedback not found with clinicId: " + clinicId);
        }
        return ResponseEntity.ok(feedbackDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbackByUserId(@PathVariable long userId) {
        List<FeedbackDTO> feedbackDTO = feedbackService.getAllFeedbackByUserId(userId);
        if (feedbackDTO == null) {
            throw new CategoryNotFoundException("Feedback not found with userId: " + userId);
        }
        return ResponseEntity.ok(feedbackDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        try {
            feedbackService.addFeedback(feedbackDTO);
            return ResponseEntity.ok("Feedback added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding feedback: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Integer id, @RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.updateFeedback(id, feedbackDTO);
        return ResponseEntity.ok(feedbackDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByRating(@PathVariable int rating) {
        List<FeedbackDTO> feedbackDTO = feedbackService.getFeedbackByRating(rating);
        if (feedbackDTO == null) {
            throw new CategoryNotFoundException("Feedback not found with rating: " + rating);
        }
        return ResponseEntity.ok(feedbackDTO);
    }
}
