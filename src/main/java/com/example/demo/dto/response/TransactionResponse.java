package com.example.demo.dto.response;

import com.example.demo.constant.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class TransactionResponse {
    private String id;
    private TransactionType type;
    private LocalDateTime transactionDate;
    private List<TransactionDetailResponse> details = new ArrayList<>();
}
