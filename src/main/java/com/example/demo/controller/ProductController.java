package com.example.demo.controller;

import com.example.demo.config.BaseResponse;
import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<BaseResponse<ProductResponse>> createProduct(@RequestBody ProductRequest request) {
        ProductResponse response = productService.create(request);
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> getProduct(@PathVariable String id) {
        ProductResponse response = productService.getById(id);
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> responses = productService.getAll();
        return ResponseEntity.ok(BaseResponse.success(responses));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponse>> updateProduct(@PathVariable String id,
                                                                       @RequestBody ProductRequest request) {
        ProductResponse response = productService.update(id, request);
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}
