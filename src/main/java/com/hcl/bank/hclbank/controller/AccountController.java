package com.hcl.bank.hclbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bank.hclbank.entity.dto.FundTransferDto;
import com.hcl.bank.hclbank.service.HclbankService;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private HclbankService hclbankService;

	/**
	 * This method is used for fund transfer from one account to another account.
	 * @param fundTransferDto
	 * @return Success message
	 *
	 */
	@PostMapping("/transfer")
	public ResponseEntity<String> fundTransfer(@RequestBody FundTransferDto fundTransferDto) {
		hclbankService.fundTransfer(fundTransferDto);
		return new ResponseEntity<String>("Sucessfully Transferred", HttpStatus.OK);
	}
}
