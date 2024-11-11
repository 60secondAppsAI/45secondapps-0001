package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.ApplicationDeveloper;
import com.45secondapps.dto.ApplicationDeveloperDTO;
import com.45secondapps.dto.ApplicationDeveloperSearchDTO;
import com.45secondapps.dto.ApplicationDeveloperPageDTO;
import com.45secondapps.dto.ApplicationDeveloperConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ApplicationDeveloperService extends GenericService<ApplicationDeveloper, Integer> {

	List<ApplicationDeveloper> findAll();

	ResultDTO addApplicationDeveloper(ApplicationDeveloperDTO applicationDeveloperDTO, RequestDTO requestDTO);

	ResultDTO updateApplicationDeveloper(ApplicationDeveloperDTO applicationDeveloperDTO, RequestDTO requestDTO);

    Page<ApplicationDeveloper> getAllApplicationDevelopers(Pageable pageable);

    Page<ApplicationDeveloper> getAllApplicationDevelopers(Specification<ApplicationDeveloper> spec, Pageable pageable);

	ResponseEntity<ApplicationDeveloperPageDTO> getApplicationDevelopers(ApplicationDeveloperSearchDTO applicationDeveloperSearchDTO);
	
	List<ApplicationDeveloperDTO> convertApplicationDevelopersToApplicationDeveloperDTOs(List<ApplicationDeveloper> applicationDevelopers, ApplicationDeveloperConvertCriteriaDTO convertCriteria);

	ApplicationDeveloperDTO getApplicationDeveloperDTOById(Integer applicationDeveloperId);







}





