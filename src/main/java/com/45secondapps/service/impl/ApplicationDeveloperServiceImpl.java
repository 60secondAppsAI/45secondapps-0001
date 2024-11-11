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
import com.45secondapps.dao.ApplicationDeveloperDAO;
import com.45secondapps.domain.ApplicationDeveloper;
import com.45secondapps.dto.ApplicationDeveloperDTO;
import com.45secondapps.dto.ApplicationDeveloperSearchDTO;
import com.45secondapps.dto.ApplicationDeveloperPageDTO;
import com.45secondapps.dto.ApplicationDeveloperConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.ApplicationDeveloperService;
import com.45secondapps.util.ControllerUtils;





@Service
public class ApplicationDeveloperServiceImpl extends GenericServiceImpl<ApplicationDeveloper, Integer> implements ApplicationDeveloperService {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationDeveloperServiceImpl.class);

	@Autowired
	ApplicationDeveloperDAO applicationDeveloperDao;

	


	@Override
	public GenericDAO<ApplicationDeveloper, Integer> getDAO() {
		return (GenericDAO<ApplicationDeveloper, Integer>) applicationDeveloperDao;
	}
	
	public List<ApplicationDeveloper> findAll () {
		List<ApplicationDeveloper> applicationDevelopers = applicationDeveloperDao.findAll();
		
		return applicationDevelopers;	
		
	}

	public ResultDTO addApplicationDeveloper(ApplicationDeveloperDTO applicationDeveloperDTO, RequestDTO requestDTO) {

		ApplicationDeveloper applicationDeveloper = new ApplicationDeveloper();

		applicationDeveloper.setApplicationDeveloperId(applicationDeveloperDTO.getApplicationDeveloperId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		applicationDeveloper = applicationDeveloperDao.save(applicationDeveloper);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ApplicationDeveloper> getAllApplicationDevelopers(Pageable pageable) {
		return applicationDeveloperDao.findAll(pageable);
	}

	public Page<ApplicationDeveloper> getAllApplicationDevelopers(Specification<ApplicationDeveloper> spec, Pageable pageable) {
		return applicationDeveloperDao.findAll(spec, pageable);
	}

	public ResponseEntity<ApplicationDeveloperPageDTO> getApplicationDevelopers(ApplicationDeveloperSearchDTO applicationDeveloperSearchDTO) {
	
			Integer applicationDeveloperId = applicationDeveloperSearchDTO.getApplicationDeveloperId(); 
 			String sortBy = applicationDeveloperSearchDTO.getSortBy();
			String sortOrder = applicationDeveloperSearchDTO.getSortOrder();
			String searchQuery = applicationDeveloperSearchDTO.getSearchQuery();
			Integer page = applicationDeveloperSearchDTO.getPage();
			Integer size = applicationDeveloperSearchDTO.getSize();

	        Specification<ApplicationDeveloper> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, applicationDeveloperId, "applicationDeveloperId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<ApplicationDeveloper> applicationDevelopers = this.getAllApplicationDevelopers(spec, pageable);
		
		//System.out.println(String.valueOf(applicationDevelopers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(applicationDevelopers.getTotalPages()));
		
		List<ApplicationDeveloper> applicationDevelopersList = applicationDevelopers.getContent();
		
		ApplicationDeveloperConvertCriteriaDTO convertCriteria = new ApplicationDeveloperConvertCriteriaDTO();
		List<ApplicationDeveloperDTO> applicationDeveloperDTOs = this.convertApplicationDevelopersToApplicationDeveloperDTOs(applicationDevelopersList,convertCriteria);
		
		ApplicationDeveloperPageDTO applicationDeveloperPageDTO = new ApplicationDeveloperPageDTO();
		applicationDeveloperPageDTO.setApplicationDevelopers(applicationDeveloperDTOs);
		applicationDeveloperPageDTO.setTotalElements(applicationDevelopers.getTotalElements());
		return ResponseEntity.ok(applicationDeveloperPageDTO);
	}

	public List<ApplicationDeveloperDTO> convertApplicationDevelopersToApplicationDeveloperDTOs(List<ApplicationDeveloper> applicationDevelopers, ApplicationDeveloperConvertCriteriaDTO convertCriteria) {
		
		List<ApplicationDeveloperDTO> applicationDeveloperDTOs = new ArrayList<ApplicationDeveloperDTO>();
		
		for (ApplicationDeveloper applicationDeveloper : applicationDevelopers) {
			applicationDeveloperDTOs.add(convertApplicationDeveloperToApplicationDeveloperDTO(applicationDeveloper,convertCriteria));
		}
		
		return applicationDeveloperDTOs;

	}
	
	public ApplicationDeveloperDTO convertApplicationDeveloperToApplicationDeveloperDTO(ApplicationDeveloper applicationDeveloper, ApplicationDeveloperConvertCriteriaDTO convertCriteria) {
		
		ApplicationDeveloperDTO applicationDeveloperDTO = new ApplicationDeveloperDTO();
		
		applicationDeveloperDTO.setApplicationDeveloperId(applicationDeveloper.getApplicationDeveloperId());

	

		
		return applicationDeveloperDTO;
	}

	public ResultDTO updateApplicationDeveloper(ApplicationDeveloperDTO applicationDeveloperDTO, RequestDTO requestDTO) {
		
		ApplicationDeveloper applicationDeveloper = applicationDeveloperDao.getById(applicationDeveloperDTO.getApplicationDeveloperId());

		applicationDeveloper.setApplicationDeveloperId(ControllerUtils.setValue(applicationDeveloper.getApplicationDeveloperId(), applicationDeveloperDTO.getApplicationDeveloperId()));



        applicationDeveloper = applicationDeveloperDao.save(applicationDeveloper);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ApplicationDeveloperDTO getApplicationDeveloperDTOById(Integer applicationDeveloperId) {
	
		ApplicationDeveloper applicationDeveloper = applicationDeveloperDao.getById(applicationDeveloperId);
			
		
		ApplicationDeveloperConvertCriteriaDTO convertCriteria = new ApplicationDeveloperConvertCriteriaDTO();
		return(this.convertApplicationDeveloperToApplicationDeveloperDTO(applicationDeveloper,convertCriteria));
	}







}
