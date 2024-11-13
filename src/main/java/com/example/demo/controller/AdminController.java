package com.example.demo.controller;

import com.example.demo.config.BaseResponse;
import com.example.demo.config.ErrorCode;
import com.example.demo.dto.request.AdminRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.AdminResponse;
import com.example.demo.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<AdminResponse>> register(@Valid @RequestBody AdminRequest request) {
        try {
            log.info("Registering admin with email: {}", request.getEmail());
            AdminResponse response = adminService.create(request);
            return ResponseEntity.ok(BaseResponse.success(response));
        } catch (Exception e) {
            log.error("Error registering admin: ", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AdminResponse>> login(@RequestBody LoginRequest request) {
        try {
            AdminResponse response = adminService.login(request);
            return ResponseEntity.ok(BaseResponse.success(response));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<String>> logout(HttpServletRequest request) {
        try {
            request.getSession().invalidate();
            return ResponseEntity.ok(BaseResponse.success("Logout successful"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }
}