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

import com.45secondapps.domain.AppComponent;
import com.45secondapps.dto.AppComponentDTO;
import com.45secondapps.dto.AppComponentSearchDTO;
import com.45secondapps.dto.AppComponentPageDTO;
import com.45secondapps.service.AppComponentService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/appComponent")
@RestController
public class AppComponentController {

	private final static Logger logger = LoggerFactory.getLogger(AppComponentController.class);

	@Autowired
	AppComponentService appComponentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<AppComponent> getAll() {

		List<AppComponent> appComponents = appComponentService.findAll();
		
		return appComponents;	
	}

	@GetMapping(value = "/{appComponentId}")
	@ResponseBody
	public AppComponentDTO getAppComponent(@PathVariable Integer appComponentId) {
		
		return (appComponentService.getAppComponentDTOById(appComponentId));
	}

 	@RequestMapping(value = "/addAppComponent", method = RequestMethod.POST)
	public ResponseEntity<?> addAppComponent(@RequestBody AppComponentDTO appComponentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = appComponentService.addAppComponent(appComponentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/appComponents")
	public ResponseEntity<AppComponentPageDTO> getAppComponents(AppComponentSearchDTO appComponentSearchDTO) {
 
		return appComponentService.getAppComponents(appComponentSearchDTO);
	}	

	@RequestMapping(value = "/updateAppComponent", method = RequestMethod.POST)
	public ResponseEntity<?> updateAppComponent(@RequestBody AppComponentDTO appComponentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = appComponentService.updateAppComponent(appComponentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
