package com.example.ecommercebe.entities;

import com.example.ecommercebe.entities.base.BaseEntity;
import com.example.ecommercebe.statics.enums.ProductSimpleStatus;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String slug;
    private String description;
    private String detail;
    private String thumbnails; // nhiều ảnh cách nhau bởi dấu ,
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private ProductSimpleStatus status;

}
