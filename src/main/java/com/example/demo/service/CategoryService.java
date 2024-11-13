package com.example.demo.service;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);
    CategoryResponse getById(String id);
    List<CategoryResponse> getAll();
    CategoryResponse update(String id, CategoryRequest request);
    void delete(String id);
}
