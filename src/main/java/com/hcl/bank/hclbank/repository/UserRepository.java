package com.hcl.bank.hclbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bank.hclbank.entity.User;

/**
 * @author Administrator
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByUserName(String userName);

}
