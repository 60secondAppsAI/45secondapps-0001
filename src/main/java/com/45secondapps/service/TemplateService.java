package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Template;
import com.45secondapps.dto.TemplateDTO;
import com.45secondapps.dto.TemplateSearchDTO;
import com.45secondapps.dto.TemplatePageDTO;
import com.45secondapps.dto.TemplateConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TemplateService extends GenericService<Template, Integer> {

	List<Template> findAll();

	ResultDTO addTemplate(TemplateDTO templateDTO, RequestDTO requestDTO);

	ResultDTO updateTemplate(TemplateDTO templateDTO, RequestDTO requestDTO);

    Page<Template> getAllTemplates(Pageable pageable);

    Page<Template> getAllTemplates(Specification<Template> spec, Pageable pageable);

	ResponseEntity<TemplatePageDTO> getTemplates(TemplateSearchDTO templateSearchDTO);
	
	List<TemplateDTO> convertTemplatesToTemplateDTOs(List<Template> templates, TemplateConvertCriteriaDTO convertCriteria);

	TemplateDTO getTemplateDTOById(Integer templateId);







}





