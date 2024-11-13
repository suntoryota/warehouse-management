package com.example.demo.service.impl;

import com.example.demo.constant.Gender;
import com.example.demo.dto.request.AdminRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.AdminResponse;
import com.example.demo.entity.Admin;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponse create(AdminRequest request) {
        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new GlobalExceptionHandler.BadRequestException("Email already exists");
        }

        Admin admin = new Admin();
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setEmail(request.getEmail());
        admin.setBirthDate(request.getBirthDate());
        admin.setGender(Gender.valueOf(request.getGender()));
        admin.setPassword(passwordEncoder.encode(request.getPassword()));

        Admin savedAdmin = adminRepository.save(admin);

        AdminResponse response = new AdminResponse();
        response.setId(String.valueOf(savedAdmin.getId()));
        response.setFirstName(savedAdmin.getFirstName());
        response.setLastName(savedAdmin.getLastName());
        response.setEmail(savedAdmin.getEmail());
        response.setBirthDate(savedAdmin.getBirthDate());
        response.setGender(Gender.valueOf(String.valueOf(savedAdmin.getGender())));
        return response;
    }

    @Override
    public AdminResponse getById(String id) {
        Admin admin = adminRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Admin not found"));

        AdminResponse response = new AdminResponse();
        response.setId(String.valueOf(admin.getId()));
        response.setFirstName(admin.getFirstName());
        response.setLastName(admin.getLastName());
        response.setEmail(admin.getEmail());
        response.setBirthDate(admin.getBirthDate());
        response.setGender(Gender.valueOf(String.valueOf(admin.getGender())));
        return response;
    }

    @Override
    public List<AdminResponse> getAll() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminResponse> responses = new ArrayList<>();

        for (Admin admin : admins) {
            AdminResponse response = new AdminResponse();
            response.setId(String.valueOf(admin.getId()));
            response.setFirstName(admin.getFirstName());
            response.setLastName(admin.getLastName());
            response.setEmail(admin.getEmail());
            response.setBirthDate(admin.getBirthDate());
            response.setGender(Gender.valueOf(String.valueOf(admin.getGender())));
            responses.add(response);
        }

        return responses;
    }

    @Override
    public AdminResponse update(String id, AdminRequest request) {
        Admin admin = adminRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!admin.getEmail().equals(request.getEmail()) &&
                adminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setEmail(request.getEmail());
        admin.setBirthDate(request.getBirthDate());
        admin.setGender(Gender.valueOf(request.getGender()));

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        Admin updatedAdmin = adminRepository.save(admin);

        AdminResponse response = new AdminResponse();
        response.setId(String.valueOf(updatedAdmin.getId()));
        response.setFirstName(updatedAdmin.getFirstName());
        response.setLastName(updatedAdmin.getLastName());
        response.setEmail(updatedAdmin.getEmail());
        response.setBirthDate(updatedAdmin.getBirthDate());
        response.setGender(Gender.valueOf(String.valueOf(updatedAdmin.getGender())));
        return response;
    }

    @Override
    public void delete(String id) {
        Admin admin = adminRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        adminRepository.delete(admin);
    }

    @Override
    public AdminResponse login(LoginRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GlobalExceptionHandler.BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new GlobalExceptionHandler.BadRequestException("Invalid credentials");
        }

        AdminResponse response = new AdminResponse();
        response.setId(String.valueOf(admin.getId()));
        response.setFirstName(admin.getFirstName());
        response.setLastName(admin.getLastName());
        response.setEmail(admin.getEmail());
        response.setBirthDate(admin.getBirthDate());
        response.setGender(Gender.valueOf(String.valueOf(admin.getGender())));
        return response;
    }
}
