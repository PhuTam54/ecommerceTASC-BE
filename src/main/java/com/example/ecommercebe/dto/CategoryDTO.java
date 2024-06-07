package com.example.ecommercebe.dto;

import com.example.ecommercebe.entities.Category;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Positive(message = "Parent_id must be greater than Zero")
    @Nullable()
    private Integer parent_id;
    private Integer id;
    private List<CategoryDTO> childrenNames;
    public CategoryDTO(Integer id, String name, Integer parentId, List<CategoryDTO> childrenNames) {
        this.id = id;
        this.name = name;
        this.parent_id = parentId;
        this.childrenNames = childrenNames;
    }
}
