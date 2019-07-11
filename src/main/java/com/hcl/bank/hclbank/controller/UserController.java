package com.hcl.bank.hclbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bank.hclbank.entity.Transaction;
import com.hcl.bank.hclbank.entity.dto.LoginDto;
import com.hcl.bank.hclbank.entity.dto.UserDto;
import com.hcl.bank.hclbank.service.HclbankService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private HclbankService hclbankService;

	@PostMapping("/add")
	public String createUser(@RequestBody UserDto userDto) {
		return hclbankService.createUser(userDto);
	}
	
	@PostMapping("/login")
	public List<Transaction> userLogin(@RequestBody LoginDto loginDto){
		return hclbankService.userLogin(loginDto);
	}
}
