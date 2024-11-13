package com.example.demo.service.impl;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category savedCategory = categoryRepository.save(category);

        CategoryResponse response = new CategoryResponse();
        response.setId(String.valueOf(savedCategory.getId()));
        response.setName(savedCategory.getName());
        response.setDescription(savedCategory.getDescription());
        return response;
    }

    @Override
    public CategoryResponse getById(String id) {
        Category category = categoryRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CategoryResponse response = new CategoryResponse();
        response.setId(String.valueOf(category.getId()));
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponse response = new CategoryResponse();
            response.setId(String.valueOf(category.getId()));
            response.setName(category.getName());
            response.setDescription(category.getDescription());
            responses.add(response);
        }

        return responses;
    }

    @Override
    public CategoryResponse update(String id, CategoryRequest request) {
        Category category = categoryRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        CategoryResponse response = new CategoryResponse();
        response.setId(String.valueOf(updatedCategory.getId()));
        response.setName(updatedCategory.getName());
        response.setDescription(updatedCategory.getDescription());
        return response;
    }

    @Override
    public void delete(String id) {
        Category category = categoryRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }
}
