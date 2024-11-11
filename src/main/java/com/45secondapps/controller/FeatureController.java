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

import com.45secondapps.domain.Feature;
import com.45secondapps.dto.FeatureDTO;
import com.45secondapps.dto.FeatureSearchDTO;
import com.45secondapps.dto.FeaturePageDTO;
import com.45secondapps.service.FeatureService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/feature")
@RestController
public class FeatureController {

	private final static Logger logger = LoggerFactory.getLogger(FeatureController.class);

	@Autowired
	FeatureService featureService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Feature> getAll() {

		List<Feature> features = featureService.findAll();
		
		return features;	
	}

	@GetMapping(value = "/{featureId}")
	@ResponseBody
	public FeatureDTO getFeature(@PathVariable Integer featureId) {
		
		return (featureService.getFeatureDTOById(featureId));
	}

 	@RequestMapping(value = "/addFeature", method = RequestMethod.POST)
	public ResponseEntity<?> addFeature(@RequestBody FeatureDTO featureDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = featureService.addFeature(featureDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/features")
	public ResponseEntity<FeaturePageDTO> getFeatures(FeatureSearchDTO featureSearchDTO) {
 
		return featureService.getFeatures(featureSearchDTO);
	}	

	@RequestMapping(value = "/updateFeature", method = RequestMethod.POST)
	public ResponseEntity<?> updateFeature(@RequestBody FeatureDTO featureDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = featureService.updateFeature(featureDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
