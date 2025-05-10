package com.zimsec.Security.Accounts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class AccountsController {
    private final AccountsService accountsService;

    AccountsController(AccountsService accountsService){
        this.accountsService = accountsService;
    }

    @PostMapping("/balance")
    public ResponseEntity<BalanceDto> getAccountBalance(@RequestBody BalanceRequestDto balanceRequestDto
    ){
        return ResponseEntity.ok(accountsService.getBalance(balanceRequestDto.id(),balanceRequestDto.email()));
    }

    @PostMapping("/top-up")
    public ResponseEntity<Double> top_up_balance(@RequestBody TopUpBalanceRequestDto user_dto){
        return ResponseEntity.ok(accountsService.addDeposit(user_dto.user(), user_dto.top_amount()));
    }

    @PostMapping("/send/money")
    public ResponseEntity<?> sendMoneyToAnother(@RequestBody SendMoneyDto sendMoneyDto){
        return accountsService.sendMoney(sendMoneyDto.sender_id(), sendMoneyDto.sender_email(), sendMoneyDto.receiver_account(), sendMoneyDto.amount_sent());
    }
}
