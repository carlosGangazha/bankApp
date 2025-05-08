package com.zimsec.Security.Banking;

import com.zimsec.Security.Accounts.AccountModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", referencedColumnName = "account_Id")
    private AccountModel senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id", referencedColumnName = "account_Id")
    private AccountModel receiverAccount;

    private double amount;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountModel getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(AccountModel senderAccount) {
        this.senderAccount = senderAccount;
    }

    public AccountModel getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(AccountModel receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
