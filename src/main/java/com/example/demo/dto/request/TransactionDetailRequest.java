package com.example.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDetailRequest {
    @NotNull
    private Long productId;

    @Min(1)
    private Integer quantity;
}
