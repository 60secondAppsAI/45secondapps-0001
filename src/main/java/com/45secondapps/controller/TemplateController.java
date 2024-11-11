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

import com.45secondapps.domain.Template;
import com.45secondapps.dto.TemplateDTO;
import com.45secondapps.dto.TemplateSearchDTO;
import com.45secondapps.dto.TemplatePageDTO;
import com.45secondapps.service.TemplateService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/template")
@RestController
public class TemplateController {

	private final static Logger logger = LoggerFactory.getLogger(TemplateController.class);

	@Autowired
	TemplateService templateService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Template> getAll() {

		List<Template> templates = templateService.findAll();
		
		return templates;	
	}

	@GetMapping(value = "/{templateId}")
	@ResponseBody
	public TemplateDTO getTemplate(@PathVariable Integer templateId) {
		
		return (templateService.getTemplateDTOById(templateId));
	}

 	@RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
	public ResponseEntity<?> addTemplate(@RequestBody TemplateDTO templateDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = templateService.addTemplate(templateDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/templates")
	public ResponseEntity<TemplatePageDTO> getTemplates(TemplateSearchDTO templateSearchDTO) {
 
		return templateService.getTemplates(templateSearchDTO);
	}	

	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST)
	public ResponseEntity<?> updateTemplate(@RequestBody TemplateDTO templateDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = templateService.updateTemplate(templateDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
