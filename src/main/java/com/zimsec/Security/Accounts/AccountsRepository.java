package com.zimsec.Security.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<AccountModel, Integer> {
}
