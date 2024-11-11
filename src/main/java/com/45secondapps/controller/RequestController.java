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

import com.45secondapps.domain.Request;
import com.45secondapps.dto.RequestDTO;
import com.45secondapps.dto.RequestSearchDTO;
import com.45secondapps.dto.RequestPageDTO;
import com.45secondapps.service.RequestService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/request")
@RestController
public class RequestController {

	private final static Logger logger = LoggerFactory.getLogger(RequestController.class);

	@Autowired
	RequestService requestService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Request> getAll() {

		List<Request> requests = requestService.findAll();
		
		return requests;	
	}

	@GetMapping(value = "/{requestId}")
	@ResponseBody
	public RequestDTO getRequest(@PathVariable Integer requestId) {
		
		return (requestService.getRequestDTOById(requestId));
	}

 	@RequestMapping(value = "/addRequest", method = RequestMethod.POST)
	public ResponseEntity<?> addRequest(@RequestBody RequestDTO requestDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = requestService.addRequest(requestDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/requests")
	public ResponseEntity<RequestPageDTO> getRequests(RequestSearchDTO requestSearchDTO) {
 
		return requestService.getRequests(requestSearchDTO);
	}	

	@RequestMapping(value = "/updateRequest", method = RequestMethod.POST)
	public ResponseEntity<?> updateRequest(@RequestBody RequestDTO requestDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = requestService.updateRequest(requestDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
