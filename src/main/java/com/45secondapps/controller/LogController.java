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

import com.45secondapps.domain.Log;
import com.45secondapps.dto.LogDTO;
import com.45secondapps.dto.LogSearchDTO;
import com.45secondapps.dto.LogPageDTO;
import com.45secondapps.service.LogService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/log")
@RestController
public class LogController {

	private final static Logger logger = LoggerFactory.getLogger(LogController.class);

	@Autowired
	LogService logService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Log> getAll() {

		List<Log> logs = logService.findAll();
		
		return logs;	
	}

	@GetMapping(value = "/{logId}")
	@ResponseBody
	public LogDTO getLog(@PathVariable Integer logId) {
		
		return (logService.getLogDTOById(logId));
	}

 	@RequestMapping(value = "/addLog", method = RequestMethod.POST)
	public ResponseEntity<?> addLog(@RequestBody LogDTO logDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = logService.addLog(logDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/logs")
	public ResponseEntity<LogPageDTO> getLogs(LogSearchDTO logSearchDTO) {
 
		return logService.getLogs(logSearchDTO);
	}	

	@RequestMapping(value = "/updateLog", method = RequestMethod.POST)
	public ResponseEntity<?> updateLog(@RequestBody LogDTO logDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = logService.updateLog(logDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
