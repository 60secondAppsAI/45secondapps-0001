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

import com.45secondapps.domain.Component;
import com.45secondapps.dto.ComponentDTO;
import com.45secondapps.dto.ComponentSearchDTO;
import com.45secondapps.dto.ComponentPageDTO;
import com.45secondapps.service.ComponentService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/component")
@RestController
public class ComponentController {

	private final static Logger logger = LoggerFactory.getLogger(ComponentController.class);

	@Autowired
	ComponentService componentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Component> getAll() {

		List<Component> components = componentService.findAll();
		
		return components;	
	}

	@GetMapping(value = "/{componentId}")
	@ResponseBody
	public ComponentDTO getComponent(@PathVariable Integer componentId) {
		
		return (componentService.getComponentDTOById(componentId));
	}

 	@RequestMapping(value = "/addComponent", method = RequestMethod.POST)
	public ResponseEntity<?> addComponent(@RequestBody ComponentDTO componentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = componentService.addComponent(componentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/components")
	public ResponseEntity<ComponentPageDTO> getComponents(ComponentSearchDTO componentSearchDTO) {
 
		return componentService.getComponents(componentSearchDTO);
	}	

	@RequestMapping(value = "/updateComponent", method = RequestMethod.POST)
	public ResponseEntity<?> updateComponent(@RequestBody ComponentDTO componentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = componentService.updateComponent(componentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
