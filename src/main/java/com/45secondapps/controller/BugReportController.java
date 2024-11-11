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

import com.45secondapps.domain.BugReport;
import com.45secondapps.dto.BugReportDTO;
import com.45secondapps.dto.BugReportSearchDTO;
import com.45secondapps.dto.BugReportPageDTO;
import com.45secondapps.service.BugReportService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/bugReport")
@RestController
public class BugReportController {

	private final static Logger logger = LoggerFactory.getLogger(BugReportController.class);

	@Autowired
	BugReportService bugReportService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<BugReport> getAll() {

		List<BugReport> bugReports = bugReportService.findAll();
		
		return bugReports;	
	}

	@GetMapping(value = "/{bugReportId}")
	@ResponseBody
	public BugReportDTO getBugReport(@PathVariable Integer bugReportId) {
		
		return (bugReportService.getBugReportDTOById(bugReportId));
	}

 	@RequestMapping(value = "/addBugReport", method = RequestMethod.POST)
	public ResponseEntity<?> addBugReport(@RequestBody BugReportDTO bugReportDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bugReportService.addBugReport(bugReportDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/bugReports")
	public ResponseEntity<BugReportPageDTO> getBugReports(BugReportSearchDTO bugReportSearchDTO) {
 
		return bugReportService.getBugReports(bugReportSearchDTO);
	}	

	@RequestMapping(value = "/updateBugReport", method = RequestMethod.POST)
	public ResponseEntity<?> updateBugReport(@RequestBody BugReportDTO bugReportDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bugReportService.updateBugReport(bugReportDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
