package com.example.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank
    private String name;
    private String description;
    private String imageUrl;
    @NotNull
    private Long categoryId;
    @Min(0)
    private Integer stock;
}
