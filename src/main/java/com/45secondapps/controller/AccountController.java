package com.45secondapps.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.45secondapps.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.45secondapps.domain.Account;
import com.45secondapps.dto.AccountDTO;
import com.45secondapps.dto.AccountSearchDTO;
import com.45secondapps.dto.AccountPageDTO;
import com.45secondapps.service.AccountService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/account")
@RestController
public class AccountController {

	private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	AccountService accountService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Account> getAll() {

		List<Account> accounts = accountService.findAll();
		
		return accounts;	
	}

	@GetMapping(value = "/{accountId}")
	@ResponseBody
	public AccountDTO getAccount(@PathVariable Integer accountId) {
		
		return (accountService.getAccountDTOById(accountId));
	}

 	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public ResponseEntity<?> addAccount(@RequestBody AccountDTO accountDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = accountService.addAccount(accountDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/accounts")
	public ResponseEntity<AccountPageDTO> getAccounts(AccountSearchDTO accountSearchDTO) {
 
		return accountService.getAccounts(accountSearchDTO);
	}	

	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public ResponseEntity<?> updateAccount(@RequestBody AccountDTO accountDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = accountService.updateAccount(accountDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
