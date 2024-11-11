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

import com.45secondapps.domain.ApplicationDeveloper;
import com.45secondapps.dto.ApplicationDeveloperDTO;
import com.45secondapps.dto.ApplicationDeveloperSearchDTO;
import com.45secondapps.dto.ApplicationDeveloperPageDTO;
import com.45secondapps.service.ApplicationDeveloperService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/applicationDeveloper")
@RestController
public class ApplicationDeveloperController {

	private final static Logger logger = LoggerFactory.getLogger(ApplicationDeveloperController.class);

	@Autowired
	ApplicationDeveloperService applicationDeveloperService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ApplicationDeveloper> getAll() {

		List<ApplicationDeveloper> applicationDevelopers = applicationDeveloperService.findAll();
		
		return applicationDevelopers;	
	}

	@GetMapping(value = "/{applicationDeveloperId}")
	@ResponseBody
	public ApplicationDeveloperDTO getApplicationDeveloper(@PathVariable Integer applicationDeveloperId) {
		
		return (applicationDeveloperService.getApplicationDeveloperDTOById(applicationDeveloperId));
	}

 	@RequestMapping(value = "/addApplicationDeveloper", method = RequestMethod.POST)
	public ResponseEntity<?> addApplicationDeveloper(@RequestBody ApplicationDeveloperDTO applicationDeveloperDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = applicationDeveloperService.addApplicationDeveloper(applicationDeveloperDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/applicationDevelopers")
	public ResponseEntity<ApplicationDeveloperPageDTO> getApplicationDevelopers(ApplicationDeveloperSearchDTO applicationDeveloperSearchDTO) {
 
		return applicationDeveloperService.getApplicationDevelopers(applicationDeveloperSearchDTO);
	}	

	@RequestMapping(value = "/updateApplicationDeveloper", method = RequestMethod.POST)
	public ResponseEntity<?> updateApplicationDeveloper(@RequestBody ApplicationDeveloperDTO applicationDeveloperDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = applicationDeveloperService.updateApplicationDeveloper(applicationDeveloperDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
