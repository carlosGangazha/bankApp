package com.zimsec.Security.Accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zimsec.Security.Banking.TransactionModel;
import com.zimsec.Security.userAuth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_accounts")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "account_seq", sequenceName = "account_sequence", allocationSize = 20, initialValue = 1000)
    private Integer account_Id;
    private double balance;
    //mapping user to the account
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "senderAccount")
    @JsonIgnore
    private List<TransactionModel> sentTransactions;
    @OneToMany(mappedBy = "receiverAccount")
    @JsonIgnore
    private List<TransactionModel> receivedTransactions;
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getAccount_Id() {
        return account_Id;
    }
}
