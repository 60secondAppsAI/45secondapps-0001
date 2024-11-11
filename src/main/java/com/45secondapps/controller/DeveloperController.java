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

import com.45secondapps.domain.Developer;
import com.45secondapps.dto.DeveloperDTO;
import com.45secondapps.dto.DeveloperSearchDTO;
import com.45secondapps.dto.DeveloperPageDTO;
import com.45secondapps.service.DeveloperService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/developer")
@RestController
public class DeveloperController {

	private final static Logger logger = LoggerFactory.getLogger(DeveloperController.class);

	@Autowired
	DeveloperService developerService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Developer> getAll() {

		List<Developer> developers = developerService.findAll();
		
		return developers;	
	}

	@GetMapping(value = "/{developerId}")
	@ResponseBody
	public DeveloperDTO getDeveloper(@PathVariable Integer developerId) {
		
		return (developerService.getDeveloperDTOById(developerId));
	}

 	@RequestMapping(value = "/addDeveloper", method = RequestMethod.POST)
	public ResponseEntity<?> addDeveloper(@RequestBody DeveloperDTO developerDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = developerService.addDeveloper(developerDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/developers")
	public ResponseEntity<DeveloperPageDTO> getDevelopers(DeveloperSearchDTO developerSearchDTO) {
 
		return developerService.getDevelopers(developerSearchDTO);
	}	

	@RequestMapping(value = "/updateDeveloper", method = RequestMethod.POST)
	public ResponseEntity<?> updateDeveloper(@RequestBody DeveloperDTO developerDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = developerService.updateDeveloper(developerDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
