package com.example.ecommercebe.dto;

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
    private List<String> childrenNames;

    public List<String> getChildrenNames() {
        return childrenNames;
    }

    public void setChildrenNames(List<String> childrenNames) {
        this.childrenNames = childrenNames;
    }
    public CategoryDTO(Integer id, String name, Integer parentId, List<String> childrenNames) {
        this.id = id;
        this.name = name;
        this.parent_id = parentId;
        this.childrenNames = childrenNames;
    }
}
