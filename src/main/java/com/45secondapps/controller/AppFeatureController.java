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

import com.45secondapps.domain.AppFeature;
import com.45secondapps.dto.AppFeatureDTO;
import com.45secondapps.dto.AppFeatureSearchDTO;
import com.45secondapps.dto.AppFeaturePageDTO;
import com.45secondapps.service.AppFeatureService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/appFeature")
@RestController
public class AppFeatureController {

	private final static Logger logger = LoggerFactory.getLogger(AppFeatureController.class);

	@Autowired
	AppFeatureService appFeatureService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<AppFeature> getAll() {

		List<AppFeature> appFeatures = appFeatureService.findAll();
		
		return appFeatures;	
	}

	@GetMapping(value = "/{appFeatureId}")
	@ResponseBody
	public AppFeatureDTO getAppFeature(@PathVariable Integer appFeatureId) {
		
		return (appFeatureService.getAppFeatureDTOById(appFeatureId));
	}

 	@RequestMapping(value = "/addAppFeature", method = RequestMethod.POST)
	public ResponseEntity<?> addAppFeature(@RequestBody AppFeatureDTO appFeatureDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = appFeatureService.addAppFeature(appFeatureDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/appFeatures")
	public ResponseEntity<AppFeaturePageDTO> getAppFeatures(AppFeatureSearchDTO appFeatureSearchDTO) {
 
		return appFeatureService.getAppFeatures(appFeatureSearchDTO);
	}	

	@RequestMapping(value = "/updateAppFeature", method = RequestMethod.POST)
	public ResponseEntity<?> updateAppFeature(@RequestBody AppFeatureDTO appFeatureDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = appFeatureService.updateAppFeature(appFeatureDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
