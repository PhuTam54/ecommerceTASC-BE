package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
}
