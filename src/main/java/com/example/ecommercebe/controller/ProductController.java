package com.example.ecommercebe.controller;
import com.example.ecommercebe.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "Product Controller")
@CrossOrigin()
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit) {
        return ResponseEntity.ok(productService.getAll(PageRequest.of(page - 1, limit)));
    }

    @RequestMapping(path = "/active", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActiveProducts(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(productService.findAllActiveProducts(name));
    }

    @RequestMapping(path = "/active/native", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActiveProductsNative(@RequestParam(name = "status") int status) {
        return ResponseEntity.ok(productService.findAllActiveProductsNative(status));
    }

//    @RequestMapping(path = "/result-dto", method = RequestMethod.GET)
//    public ResponseEntity<?> findResultDTOByCustomer(@RequestParam(name = "id") int id) {
//        return ResponseEntity.ok(productService.findResultDTOByCustomer(id));
//    }
}
