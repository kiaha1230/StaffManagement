package com.team3.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.team3.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
