package com.hcl.bank.hclbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bank.hclbank.entity.AccountDetails;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Long> {
	
	boolean existsByAccountNumber(Long accountNumber);
	
	AccountDetails findByAccountNumber(Long accountNumber);

}
