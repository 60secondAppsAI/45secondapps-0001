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

import com.45secondapps.domain.Billing;
import com.45secondapps.dto.BillingDTO;
import com.45secondapps.dto.BillingSearchDTO;
import com.45secondapps.dto.BillingPageDTO;
import com.45secondapps.service.BillingService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/billing")
@RestController
public class BillingController {

	private final static Logger logger = LoggerFactory.getLogger(BillingController.class);

	@Autowired
	BillingService billingService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Billing> getAll() {

		List<Billing> billings = billingService.findAll();
		
		return billings;	
	}

	@GetMapping(value = "/{billingId}")
	@ResponseBody
	public BillingDTO getBilling(@PathVariable Integer billingId) {
		
		return (billingService.getBillingDTOById(billingId));
	}

 	@RequestMapping(value = "/addBilling", method = RequestMethod.POST)
	public ResponseEntity<?> addBilling(@RequestBody BillingDTO billingDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = billingService.addBilling(billingDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/billings")
	public ResponseEntity<BillingPageDTO> getBillings(BillingSearchDTO billingSearchDTO) {
 
		return billingService.getBillings(billingSearchDTO);
	}	

	@RequestMapping(value = "/updateBilling", method = RequestMethod.POST)
	public ResponseEntity<?> updateBilling(@RequestBody BillingDTO billingDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = billingService.updateBilling(billingDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
