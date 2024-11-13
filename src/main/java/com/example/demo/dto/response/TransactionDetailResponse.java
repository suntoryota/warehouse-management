package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetailResponse {
    private String productId;
    private String productName;
    private Integer quantity;
}
