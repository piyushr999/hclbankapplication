package com.hcl.bank.hclbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bank.hclbank.entity.dto.FundTransferDto;
import com.hcl.bank.hclbank.service.HclbankService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private HclbankService hclbankService;
	
	@PostMapping("/transfer")
	public void fundTransfer(@RequestBody FundTransferDto fundTransferDto) {
		hclbankService.fundTransfer(fundTransferDto);
	}
}
