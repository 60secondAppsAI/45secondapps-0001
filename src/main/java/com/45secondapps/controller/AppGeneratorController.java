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

import com.45secondapps.domain.AppGenerator;
import com.45secondapps.dto.AppGeneratorDTO;
import com.45secondapps.dto.AppGeneratorSearchDTO;
import com.45secondapps.dto.AppGeneratorPageDTO;
import com.45secondapps.service.AppGeneratorService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/appGenerator")
@RestController
public class AppGeneratorController {

	private final static Logger logger = LoggerFactory.getLogger(AppGeneratorController.class);

	@Autowired
	AppGeneratorService appGeneratorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<AppGenerator> getAll() {

		List<AppGenerator> appGenerators = appGeneratorService.findAll();
		
		return appGenerators;	
	}

	@GetMapping(value = "/{appGeneratorId}")
	@ResponseBody
	public AppGeneratorDTO getAppGenerator(@PathVariable Integer appGeneratorId) {
		
		return (appGeneratorService.getAppGeneratorDTOById(appGeneratorId));
	}

 	@RequestMapping(value = "/addAppGenerator", method = RequestMethod.POST)
	public ResponseEntity<?> addAppGenerator(@RequestBody AppGeneratorDTO appGeneratorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = appGeneratorService.addAppGenerator(appGeneratorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/appGenerators")
	public ResponseEntity<AppGeneratorPageDTO> getAppGenerators(AppGeneratorSearchDTO appGeneratorSearchDTO) {
 
		return appGeneratorService.getAppGenerators(appGeneratorSearchDTO);
	}	

	@RequestMapping(value = "/updateAppGenerator", method = RequestMethod.POST)
	public ResponseEntity<?> updateAppGenerator(@RequestBody AppGeneratorDTO appGeneratorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = appGeneratorService.updateAppGenerator(appGeneratorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
