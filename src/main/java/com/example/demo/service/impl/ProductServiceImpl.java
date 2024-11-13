package com.example.demo.service.impl;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);
        product.setStock(request.getStock());

        Product savedProduct = productRepository.save(product);

        ProductResponse response = new ProductResponse();
        response.setId(String.valueOf(savedProduct.getId()));
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setCategoryId(String.valueOf(savedProduct.getCategory().getId()));
        response.setStock(savedProduct.getStock());
        return response;
    }

    @Override
    public ProductResponse getById(String id) {
        Product product = productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductResponse response = new ProductResponse();
        response.setId(String.valueOf(product.getId()));
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setImageUrl(product.getImageUrl());
        response.setCategoryId(String.valueOf(product.getCategory().getId()));
        response.setStock(product.getStock());
        return response;
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse response = new ProductResponse();
            response.setId(String.valueOf(product.getId()));
            response.setName(product.getName());
            response.setDescription(product.getDescription());
            response.setImageUrl(product.getImageUrl());
            response.setCategoryId(String.valueOf(product.getCategory().getId()));
            response.setStock(product.getStock());
            responses.add(response);
        }

        return responses;
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) {
        Product product = productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);
        product.setStock(request.getStock());

        Product updatedProduct = productRepository.save(product);

        ProductResponse response = new ProductResponse();
        response.setId(String.valueOf(updatedProduct.getId()));
        response.setName(updatedProduct.getName());
        response.setDescription(updatedProduct.getDescription());
        response.setImageUrl(updatedProduct.getImageUrl());
        response.setCategoryId(String.valueOf(updatedProduct.getCategory().getId()));
        response.setStock(updatedProduct.getStock());
        return response;
    }

    @Override
    public void delete(String id) {
        Product product = productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}