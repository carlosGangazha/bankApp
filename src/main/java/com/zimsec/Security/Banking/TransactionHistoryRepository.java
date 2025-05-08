package com.zimsec.Security.Banking;

import com.zimsec.Security.Accounts.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionModel, Long> {
    List<TransactionModel> findBySenderAccount(AccountModel senderAccount);

    // Optional: find transactions by receiver account
    List<TransactionModel> findByReceiverAccount(AccountModel receiverAccount);
}
