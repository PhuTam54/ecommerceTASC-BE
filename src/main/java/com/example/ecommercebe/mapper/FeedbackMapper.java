package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.FeedbackDTO;
import com.example.ecommercebe.entities.*;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.repositories.ClinicRepository;
import com.example.ecommercebe.repositories.FeedbackRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;


    public Feedback toEntity(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDTO.getId());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setComment(feedbackDTO.getComment());
        feedback.setCreateAt(feedbackDTO.getCreateAt());

        Product product = productRepository.findById(feedbackDTO.getProduct_id()).orElse(null);
        feedback.setProduct(product);

        Clinic clinic = clinicRepository.findById(feedbackDTO.getClinic_id()).orElse(null);
        feedback.setClinic(clinic);

        User user = userRepository.findById(feedbackDTO.getUser_id()).orElse(null);
        feedback.setUser(user);

        if (feedbackDTO.getParent_id() != null) {
            Feedback parent = feedbackRepository.findById(feedbackDTO.getParent_id())
                    .orElseThrow(() -> new CategoryNotFoundException("Parent category not found with id: " + feedbackDTO.getParent_id()));
            feedback.setParent(parent);
        }


        return feedback;
    }

    public FeedbackDTO toDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setComment(feedback.getComment());
        feedbackDTO.setCreateAt(feedback.getCreateAt());
        feedbackDTO.setProduct_id(feedback.getProduct().getId());
        feedbackDTO.setClinic_id(feedback.getClinic().getId());
        feedbackDTO.setUser_id(feedback.getUser().getId());

        if (feedback.getParent() != null) {
            feedbackDTO.setParent_id(feedback.getParent().getId());
        }

        Long parentId = (feedback.getParent() != null) ? feedback.getParent().getId() : null;
        feedbackDTO.setParent_id(parentId);
        List<String> childrenNames = feedback.getChildren().stream()
                .map(Feedback::getComment)
                .collect(Collectors.toList());
        feedbackDTO.setChildren(childrenNames);
        return feedbackDTO;
    }
}
