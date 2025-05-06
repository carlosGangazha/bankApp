package com.zimsec.Security.Accounts;

import com.zimsec.Security.userAuth.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_accounts")
public class accountModel {
    private Integer account_Id;
    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "Id")
    private User user;

}
