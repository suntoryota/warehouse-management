package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String categoryId;
    private Integer stock;
}
