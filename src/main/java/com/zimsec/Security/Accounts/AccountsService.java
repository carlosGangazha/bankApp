package com.zimsec.Security.Accounts;

import com.zimsec.Security.userAuth.User;
import com.zimsec.Security.userAuth.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final UserRepository userRepository;

    public AccountsService(AccountsRepository accountsRepository, UserRepository userRepository){
        this.accountsRepository = accountsRepository;
        this.userRepository = userRepository;
    }

    //will add some logic for error checking I don't want to make this complicated
    @Transactional
    public double addDeposit(int user_id, double amount){
        User user = userRepository.findById(user_id).orElseThrow(()-> new RuntimeException("No user is found"));
        AccountModel userAccount = user.getAccount();
        double new_amount = userAccount.getBalance() + amount;
        userAccount.setBalance(new_amount);
        accountsRepository.save(userAccount);
        return new_amount;
    }

    public double getBalance(int user_id, String user_email){
        User user = userRepository.findById(user_id).orElseThrow(()-> new RuntimeException("No user found"));
        if (!user.getUsername().equalsIgnoreCase(user_email)){
            throw new RuntimeException("Not your account");
        }else {
            AccountModel accountModel = user.getAccount();
            return accountModel.getBalance();
        }
    }
    //my logic is big coz I am checking if the user is the user using id and email
    //will do the response dto as soon as possible
    @Transactional
    public ResponseEntity<?> sendMoney(int sender_id,String sender_email,int receiver_account, double send_amount){
        User user_sender = userRepository.findById(sender_id).orElseThrow(()-> new RuntimeException("No user is found"));
        if (!user_sender.getUsername().equalsIgnoreCase(sender_email)){
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Authorized come on");
        }
        //checking if account has available balance
        AccountModel sender_account = user_sender.getAccount();
        if (sender_account.getBalance() < send_amount){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Sorry your balance is insufficient");
        }

        //looking for account to put the money
        AccountModel receiver_id = accountsRepository.findById(receiver_account).orElseThrow(()-> new RuntimeException("No account, Transaction Failed"));
        double sender_balance_after = sender_account.getBalance() - send_amount;
        double receiver_balance_after = receiver_id.getBalance() + send_amount;

        sender_account.setBalance(sender_balance_after);
        receiver_id.setBalance(receiver_balance_after);

        accountsRepository.save(sender_account);
        accountsRepository.save(receiver_id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Amount transacted, new balance" + sender_balance_after);
    }
}
