package com.hcl.bank.hclbank.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bank.hclbank.entity.AccountDetails;
import com.hcl.bank.hclbank.entity.Transaction;
import com.hcl.bank.hclbank.entity.User;
import com.hcl.bank.hclbank.entity.dto.FundTransferDto;
import com.hcl.bank.hclbank.entity.dto.LoginDto;
import com.hcl.bank.hclbank.entity.dto.UserDto;
import com.hcl.bank.hclbank.repository.AccountRepository;
import com.hcl.bank.hclbank.repository.TransactionRepository;
import com.hcl.bank.hclbank.repository.UserRepository;

@Service
public class HclbankService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public String createUser(UserDto userDto) {
		
	 
		
		if (userDto.getPwd().equals(userDto.getConfirmpwd())) {
			
			User user = new User();
			user.setUserName(userDto.getuName());
			user.setPassword(userDto.getPwd());
			user.setEmailId(userDto.getEmail());
			user.setDob(userDto.getBirthDate());
			user.setGender(userDto.getSex());
			user.setPhoneNumber(userDto.getContactNumber());
			
			while (true) {
				Long randomAccountNumber = (long) Math.floor(100000 + Math.random() * 900000);
				if (!accountRepository.existsByAccountNumber(randomAccountNumber)) {
					userRepository.save(user);
					AccountDetails accountDetails = new AccountDetails();
					accountDetails.setAccountNumber(randomAccountNumber);
					accountDetails.setAccountType("Savings");
					accountDetails.setAcountBalance(10000);
					accountDetails.setUser(user);
					accountRepository.save(accountDetails);
					break;
				}
			}
			return "User Successfully Created";
		} else {
			return "Password does not match with confirm password";
		}

	}

	public void fundTransfer(FundTransferDto fundTransferDto) {

		AccountDetails fromAccount = accountRepository.findByAccountNumber(fundTransferDto.getFromAccount());
		AccountDetails toAccount = accountRepository.findByAccountNumber(fundTransferDto.getToAccount());

		Transaction transaction = new Transaction();
		transaction.setFromAcount(fromAccount);
		transaction.setToAccount(toAccount);

		transaction.setAmount(fundTransferDto.getAmount());
		transaction.setTransactionDate(new Date());

		transactionRepository.save(transaction);

	}

	public List<Transaction> userLogin(LoginDto loginDto) {

		List<User> userList = userRepository.findByUserName(loginDto.getUserName());
		if ( !userList.isEmpty() && userList.get(0).getPassword().equals(loginDto.getPassword())) {
			Optional<AccountDetails> optionalAccDetails = accountRepository.findById(userList.get(0).getId());

			if (optionalAccDetails.isPresent()) {
				AccountDetails accountDetails = optionalAccDetails.get();
				List<?> list = transactionRepository.getTransactions(accountDetails.getId());
				List<Transaction> transList = new ArrayList<>();
				Iterator<?> it = list.iterator();
				while (it.hasNext()) {
					transList.add((Transaction) it.next());
				}

				return transList;

			}

		}
		return Collections.emptyList();
	}
}
