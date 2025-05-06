package com.zimsec.Security.Accounts;

public record SendMoneyDto(
        int sender_id,
        String sender_email,
        int receiver_account,
        double amount_sent
) {
}
