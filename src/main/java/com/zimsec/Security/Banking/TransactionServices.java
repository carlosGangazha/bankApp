package com.zimsec.Security.Banking;

import com.zimsec.Security.Accounts.AccountModel;
import com.zimsec.Security.userAuth.User;
import com.zimsec.Security.userAuth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServices {
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    //will make adjustments if needed
    public List<TransactionResponseDto> getTransactions(int id){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("No user found"));

        AccountModel accountModel = user.getAccount();

        List<TransactionModel> sent = transactionHistoryRepository.findBySenderAccount(accountModel);

        List<TransactionResponseDto> dtoList = sent.stream()
                .map(this::mapToTransactionDTO)
                .toList();

        return new ArrayList<>(dtoList);
    }

    //mapping my model tto avoid sending someone's data
    TransactionResponseDto mapToTransactionDTO(TransactionModel transaction) {
        return new TransactionResponseDto(
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCreatedAt(),
                transaction.getReceiverAccount().getAccount_Id()
        );
    }
}
