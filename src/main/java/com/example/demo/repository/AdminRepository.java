package com.example.demo.repository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}

