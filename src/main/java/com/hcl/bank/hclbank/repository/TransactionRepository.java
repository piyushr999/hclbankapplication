package com.hcl.bank.hclbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.bank.hclbank.entity.Transaction;

/**
 * @author Administrator
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query( value = "select * from Transaction where from_Account = :id or to_Account = :id order by transaction_date desc", nativeQuery = true)
	List<Transaction> getTransactions(@Param("id") Long id);

}
