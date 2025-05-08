package com.zimsec.Security.Banking;

import java.time.LocalDateTime;

public class TransactionResponseDto {
    private String description;
    private double amount;
    private LocalDateTime createdAt;
    private int receiverAccountId;

    public TransactionResponseDto(String description, double amount, LocalDateTime createdAt, int receiverAccountId) {
        this.description = description;
        this.amount = amount;
        this.createdAt = createdAt;
        this.receiverAccountId = receiverAccountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(int receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }
}
