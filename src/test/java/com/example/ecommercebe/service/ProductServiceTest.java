package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.FileUploadedDTO;
import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
@SpringBootTest(properties = {

})
@ActiveProfiles("test")
class ProductServiceTest {
    @Mock
    private FileUploadService fileUploadService;

    @InjectMocks
    private ProductService productService;

    @Test
    void givenProductDTO_whenCreateProduct_thenReturnNewProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("First Product");
        FileUploadedDTO uploadedDTO = new FileUploadedDTO("/example/path/first-product.png", "example_public_id");

        Mockito.when(fileUploadService.uploadFile(Mockito.nullable(MultipartFile.class))).thenReturn(uploadedDTO);

//        Product newProduct = productService.createProduct(productDTO);

//        Assertions.assertEquals(productDTO.getName(), newProduct.getName());
//        Assertions.assertEquals("/example/path/first-product.png", newProduct.getThumbnails());
    }
}