package com.example.ecommercebe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private long id;
    private String comment;
    private long product_id;
    private long parent_id;
    private long clinic_id;
    private long user_id;
    private int rating;
    private LocalDateTime createAt;
    private List<FeedbackDTO> children;
}
