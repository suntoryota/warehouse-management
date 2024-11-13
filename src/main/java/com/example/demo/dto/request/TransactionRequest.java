package com.example.demo.dto.request;

import com.example.demo.constant.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TransactionRequest {
    @NotNull
    private TransactionType type;

    @NotEmpty
    private List<TransactionDetailRequest> details;
}
