package com.zimsec.Security.Banking;

import com.zimsec.Security.Accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions/history")
public class TransactionsController {

    @Autowired
    private TransactionServices transactionServices;

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(@PathVariable int id){
        return ResponseEntity.ok(transactionServices.getTransactions(id));
    }
}
