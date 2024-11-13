package com.example.demo.service;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(String id);
    List<ProductResponse> getAll();
    ProductResponse update(String id, ProductRequest request);
    void delete(String id);
}