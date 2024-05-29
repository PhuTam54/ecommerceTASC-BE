package com.example.ecommercebe.repository;

import com.example.ecommercebe.EcommerceBeApplication;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.statics.enums.ProductSimpleStatus;
import com.example.ecommercebe.util.StringHelper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker();
        boolean nameExisting = false;
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String productName = faker.food().dish();

            for (Product product :
                    products) {
                if (product.getName().equals(productName)) {
                    nameExisting = true;
                    break;
                }
            }
            if (nameExisting) {
                i--;
                nameExisting = false;
                continue;
            }
            String slug = StringHelper.toSlug(productName);
            String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
            String detail = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
            BigDecimal price = new BigDecimal(faker.number().randomNumber(5, true));
            ProductSimpleStatus status = ProductSimpleStatus.ACTIVE;
            Product product = new Product();
            product.setName(productName);
//            product.setSlug(slug);
            product.setDescription(description);
//            product.setThumbnails(slug + "-image1.png");
            product.setPrice(price);
//            product.setStatus(status);
            product.setDetail(detail);
            products.add(product);
        }
        productRepository.saveAll(products);
    }
    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    // Given_When_Then
    @Test
    public void givenPageable_whenFindAllProduct_thenReturnPageProduct() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Product> productPage = productRepository.findAll(pageable);

        assertEquals(50, productPage.getTotalElements());
        assertEquals(10, productPage.getNumberOfElements());
        assertEquals(0, 1);
//        assertTrue(true);

        // junit assertion
    }
}