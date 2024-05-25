package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.FeedbackDTO;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @PostMapping("/{productId}/{clinicId}/{userId}/add")
    public ResponseEntity<?> addFeedback( @PathVariable("productId") long productId,
                                          @PathVariable("clinicId") long clinicId,
                                          @PathVariable("userId") long userId,@RequestBody FeedbackDTO feedbackDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        feedbackDTO.setProduct_id(productId);
        feedbackDTO.setClinic_id(clinicId);
        feedbackDTO.setUser_id(userId);
        feedbackService.addFeedback(feedbackDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
