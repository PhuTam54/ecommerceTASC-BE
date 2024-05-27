package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.FeedbackDTO;

import com.example.ecommercebe.entities.Feedback;

import java.util.List;

public interface FeedbackService {
    List<FeedbackDTO> getAllFeedbackByProductId(long productId);
    List<FeedbackDTO> getAllFeedbackByClinicId(long clinicId);
    List<FeedbackDTO> getAllFeedbackByUserId(long userId);
    void addFeedback(FeedbackDTO feedbackDTO);
    void updateFeedback(long id, FeedbackDTO feedbackDTO);
    void deleteFeedback(long id);
    List<FeedbackDTO> getFeedbackByRating(int rating);

}
