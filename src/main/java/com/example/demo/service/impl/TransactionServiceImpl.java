package com.example.demo.service.impl;

import com.example.demo.constant.TransactionType;
import com.example.demo.dto.request.TransactionDetailRequest;
import com.example.demo.dto.request.TransactionRequest;
import com.example.demo.dto.response.TransactionDetailResponse;
import com.example.demo.dto.response.TransactionResponse;
import com.example.demo.entity.Product;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.TransactionDetail;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setType(request.getType());
        transaction.setTransactionDate(LocalDateTime.now());

        for (TransactionDetailRequest detailRequest : request.getDetails()) {
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (request.getType() == TransactionType.STOCK_OUT &&
                    product.getStock() < detailRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            TransactionDetail detail = new TransactionDetail();
            detail.setTransaction(transaction);
            detail.setProduct(product);
            detail.setQuantity(detailRequest.getQuantity());

            transaction.getDetails().add(detail);

            // Update stock
            if (request.getType() == TransactionType.STOCK_IN) {
                product.setStock(product.getStock() + detailRequest.getQuantity());
            } else {
                product.setStock(product.getStock() - detailRequest.getQuantity());
            }
        }

        Transaction savedTransaction = transactionRepository.save(transaction);

        TransactionResponse response = new TransactionResponse();
        response.setId(String.valueOf(savedTransaction.getId()));
        response.setType(savedTransaction.getType());
        response.setTransactionDate(savedTransaction.getTransactionDate());

        List<TransactionDetailResponse> detailResponses = new ArrayList<>();
        for (TransactionDetail detail : savedTransaction.getDetails()) {
            TransactionDetailResponse detailResponse = new TransactionDetailResponse();
            detailResponse.setProductId(String.valueOf(detail.getProduct().getId()));
            detailResponse.setQuantity(detail.getQuantity());
            detailResponses.add(detailResponse);
        }
        response.setDetails(detailResponses);

        return response;
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponse> responses = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionResponse response = new TransactionResponse();
            response.setId(String.valueOf(transaction.getId()));
            response.setType(transaction.getType());
            response.setTransactionDate(transaction.getTransactionDate());

            List<TransactionDetailResponse> detailResponses = new ArrayList<>();
            for (TransactionDetail detail : transaction.getDetails()) {
                TransactionDetailResponse detailResponse = new TransactionDetailResponse();
                detailResponse.setProductId(String.valueOf(detail.getProduct().getId()));
                detailResponse.setProductName(detail.getProduct().getName());
                detailResponse.setQuantity(detail.getQuantity());
                detailResponses.add(detailResponse);
            }

            response.setDetails(detailResponses);
            responses.add(response);
        }

        return responses;
    }
}