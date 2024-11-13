package com.example.demo.service;

import com.example.demo.dto.request.AdminRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.AdminResponse;

import java.util.List;

public interface AdminService {
    AdminResponse create(AdminRequest request);
    AdminResponse getById(String id);
    List<AdminResponse> getAll();
    AdminResponse update(String id, AdminRequest request);
    void delete(String id);
    AdminResponse login(LoginRequest request);
}
