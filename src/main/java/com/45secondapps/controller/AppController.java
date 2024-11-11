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

import com.45secondapps.domain.App;
import com.45secondapps.dto.AppDTO;
import com.45secondapps.dto.AppSearchDTO;
import com.45secondapps.dto.AppPageDTO;
import com.45secondapps.service.AppService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/app")
@RestController
public class AppController {

	private final static Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	AppService appService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<App> getAll() {

		List<App> apps = appService.findAll();
		
		return apps;	
	}

	@GetMapping(value = "/{appId}")
	@ResponseBody
	public AppDTO getApp(@PathVariable Integer appId) {
		
		return (appService.getAppDTOById(appId));
	}

 	@RequestMapping(value = "/addApp", method = RequestMethod.POST)
	public ResponseEntity<?> addApp(@RequestBody AppDTO appDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = appService.addApp(appDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/apps")
	public ResponseEntity<AppPageDTO> getApps(AppSearchDTO appSearchDTO) {
 
		return appService.getApps(appSearchDTO);
	}	

	@RequestMapping(value = "/updateApp", method = RequestMethod.POST)
	public ResponseEntity<?> updateApp(@RequestBody AppDTO appDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = appService.updateApp(appDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
