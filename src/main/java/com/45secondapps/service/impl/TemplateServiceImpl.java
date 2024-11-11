package com.45secondapps.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.45secondapps.dao.GenericDAO;
import com.45secondapps.service.GenericService;
import com.45secondapps.service.impl.GenericServiceImpl;
import com.45secondapps.dao.TemplateDAO;
import com.45secondapps.domain.Template;
import com.45secondapps.dto.TemplateDTO;
import com.45secondapps.dto.TemplateSearchDTO;
import com.45secondapps.dto.TemplatePageDTO;
import com.45secondapps.dto.TemplateConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.TemplateService;
import com.45secondapps.util.ControllerUtils;





@Service
public class TemplateServiceImpl extends GenericServiceImpl<Template, Integer> implements TemplateService {

    private final static Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

	@Autowired
	TemplateDAO templateDao;

	


	@Override
	public GenericDAO<Template, Integer> getDAO() {
		return (GenericDAO<Template, Integer>) templateDao;
	}
	
	public List<Template> findAll () {
		List<Template> templates = templateDao.findAll();
		
		return templates;	
		
	}

	public ResultDTO addTemplate(TemplateDTO templateDTO, RequestDTO requestDTO) {

		Template template = new Template();

		template.setTemplateId(templateDTO.getTemplateId());


		template.setTemplateName(templateDTO.getTemplateName());


		template.setCategory(templateDTO.getCategory());


		template.setVersion(templateDTO.getVersion());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		template = templateDao.save(template);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Template> getAllTemplates(Pageable pageable) {
		return templateDao.findAll(pageable);
	}

	public Page<Template> getAllTemplates(Specification<Template> spec, Pageable pageable) {
		return templateDao.findAll(spec, pageable);
	}

	public ResponseEntity<TemplatePageDTO> getTemplates(TemplateSearchDTO templateSearchDTO) {
	
			Integer templateId = templateSearchDTO.getTemplateId(); 
 			String templateName = templateSearchDTO.getTemplateName(); 
 			String category = templateSearchDTO.getCategory(); 
 			String version = templateSearchDTO.getVersion(); 
 			String sortBy = templateSearchDTO.getSortBy();
			String sortOrder = templateSearchDTO.getSortOrder();
			String searchQuery = templateSearchDTO.getSearchQuery();
			Integer page = templateSearchDTO.getPage();
			Integer size = templateSearchDTO.getSize();

	        Specification<Template> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, templateId, "templateId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, templateName, "templateName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, category, "category"); 
			
			spec = ControllerUtils.andIfNecessary(spec, version, "version"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("templateName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("category")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("version")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Template> templates = this.getAllTemplates(spec, pageable);
		
		//System.out.println(String.valueOf(templates.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(templates.getTotalPages()));
		
		List<Template> templatesList = templates.getContent();
		
		TemplateConvertCriteriaDTO convertCriteria = new TemplateConvertCriteriaDTO();
		List<TemplateDTO> templateDTOs = this.convertTemplatesToTemplateDTOs(templatesList,convertCriteria);
		
		TemplatePageDTO templatePageDTO = new TemplatePageDTO();
		templatePageDTO.setTemplates(templateDTOs);
		templatePageDTO.setTotalElements(templates.getTotalElements());
		return ResponseEntity.ok(templatePageDTO);
	}

	public List<TemplateDTO> convertTemplatesToTemplateDTOs(List<Template> templates, TemplateConvertCriteriaDTO convertCriteria) {
		
		List<TemplateDTO> templateDTOs = new ArrayList<TemplateDTO>();
		
		for (Template template : templates) {
			templateDTOs.add(convertTemplateToTemplateDTO(template,convertCriteria));
		}
		
		return templateDTOs;

	}
	
	public TemplateDTO convertTemplateToTemplateDTO(Template template, TemplateConvertCriteriaDTO convertCriteria) {
		
		TemplateDTO templateDTO = new TemplateDTO();
		
		templateDTO.setTemplateId(template.getTemplateId());

	
		templateDTO.setTemplateName(template.getTemplateName());

	
		templateDTO.setCategory(template.getCategory());

	
		templateDTO.setVersion(template.getVersion());

	

		
		return templateDTO;
	}

	public ResultDTO updateTemplate(TemplateDTO templateDTO, RequestDTO requestDTO) {
		
		Template template = templateDao.getById(templateDTO.getTemplateId());

		template.setTemplateId(ControllerUtils.setValue(template.getTemplateId(), templateDTO.getTemplateId()));

		template.setTemplateName(ControllerUtils.setValue(template.getTemplateName(), templateDTO.getTemplateName()));

		template.setCategory(ControllerUtils.setValue(template.getCategory(), templateDTO.getCategory()));

		template.setVersion(ControllerUtils.setValue(template.getVersion(), templateDTO.getVersion()));



        template = templateDao.save(template);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TemplateDTO getTemplateDTOById(Integer templateId) {
	
		Template template = templateDao.getById(templateId);
			
		
		TemplateConvertCriteriaDTO convertCriteria = new TemplateConvertCriteriaDTO();
		return(this.convertTemplateToTemplateDTO(template,convertCriteria));
	}







}
