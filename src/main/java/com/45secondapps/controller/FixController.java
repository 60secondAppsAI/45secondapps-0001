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

import com.45secondapps.domain.Fix;
import com.45secondapps.dto.FixDTO;
import com.45secondapps.dto.FixSearchDTO;
import com.45secondapps.dto.FixPageDTO;
import com.45secondapps.service.FixService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/fix")
@RestController
public class FixController {

	private final static Logger logger = LoggerFactory.getLogger(FixController.class);

	@Autowired
	FixService fixService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Fix> getAll() {

		List<Fix> fixs = fixService.findAll();
		
		return fixs;	
	}

	@GetMapping(value = "/{fixId}")
	@ResponseBody
	public FixDTO getFix(@PathVariable Integer fixId) {
		
		return (fixService.getFixDTOById(fixId));
	}

 	@RequestMapping(value = "/addFix", method = RequestMethod.POST)
	public ResponseEntity<?> addFix(@RequestBody FixDTO fixDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = fixService.addFix(fixDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/fixs")
	public ResponseEntity<FixPageDTO> getFixs(FixSearchDTO fixSearchDTO) {
 
		return fixService.getFixs(fixSearchDTO);
	}	

	@RequestMapping(value = "/updateFix", method = RequestMethod.POST)
	public ResponseEntity<?> updateFix(@RequestBody FixDTO fixDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = fixService.updateFix(fixDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
