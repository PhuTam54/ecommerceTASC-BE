package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.FeedbackDTO;
import com.example.ecommercebe.entities.Feedback;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.mapper.FeedbackMapper;
import com.example.ecommercebe.repositories.FeedbackRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;


    @Override
    public List<FeedbackDTO> getAllFeedbackByProductId(long productId) {
            List<Feedback> feedbackList = feedbackRepository.findByProductId(productId);
            return feedbackList.stream()
                    .map(feedbackMapper::toDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDTO> getAllFeedbackByClinicId(long clinicId) {
        List<Feedback> feedbackList = feedbackRepository.findByClinicId(clinicId);
        return feedbackList.stream()
                .map(feedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDTO> getAllFeedbackByUserId(long userId) {
        List<Feedback> feedbackList = feedbackRepository.findByUserId(userId);
        return feedbackList.stream()
                .map(feedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.toEntity(feedbackDTO);
        feedback.setCreateAt(LocalDateTime.now());
        feedbackRepository.save(feedback);
    }

    @Override
    public void updateFeedback(long id, FeedbackDTO feedbackDTO) {
        Feedback existingFeedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        if (feedbackDTO.getComment() != null) {
            existingFeedback.setComment(feedbackDTO.getComment());
        }
        if (feedbackDTO.getRating() > 0) {
            existingFeedback.setRating(feedbackDTO.getRating());
        }
        feedbackDTO.setCreateAt(LocalDateTime.now());

        existingFeedback.setProduct(existingFeedback.getProduct());
        existingFeedback.setClinic(existingFeedback.getClinic());
        existingFeedback.setUser(existingFeedback.getUser());

        feedbackRepository.save(existingFeedback);
    }

    @Override
    public void deleteFeedback(long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<FeedbackDTO> getFeedbackByRating(int rating) {
        List<Feedback> feedbackList = feedbackRepository.findByRating(rating);
        return feedbackList.stream()
                .map(feedbackMapper::toDTO)
                .collect(Collectors.toList());
    }


}
