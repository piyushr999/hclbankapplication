package com.hcl.bank.hclbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bank.hclbank.entity.Transaction;
import com.hcl.bank.hclbank.entity.dto.LoginDto;
import com.hcl.bank.hclbank.entity.dto.UserDto;
import com.hcl.bank.hclbank.service.HclbankService;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private HclbankService hclbankService;

	/**
	 * This method is used to create new users with their account
	 * 
	 * @param userDto
	 * @return Success message
	 *
	 */
	@PostMapping("/add")
	public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<String>(hclbankService.createUser(userDto), HttpStatus.CREATED);
	}

	/**
	 * This method is used for login and show all transactions
	 * 
	 * @param loginDto
	 * @return List of all the transactions
	 *
	 */
	@PostMapping("/login")
	public ResponseEntity<List<Transaction>> userLogin(@RequestBody LoginDto loginDto) {
		return new ResponseEntity<List<Transaction>>(hclbankService.userLogin(loginDto), HttpStatus.OK);
	}

	@Scheduled(fixedRate = 10000)
	public void schedulerMe() {
		System.out.println("Hello");
	}
}
