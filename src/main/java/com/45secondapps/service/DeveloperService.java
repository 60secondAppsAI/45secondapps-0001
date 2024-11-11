package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Developer;
import com.45secondapps.dto.DeveloperDTO;
import com.45secondapps.dto.DeveloperSearchDTO;
import com.45secondapps.dto.DeveloperPageDTO;
import com.45secondapps.dto.DeveloperConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DeveloperService extends GenericService<Developer, Integer> {

	List<Developer> findAll();

	ResultDTO addDeveloper(DeveloperDTO developerDTO, RequestDTO requestDTO);

	ResultDTO updateDeveloper(DeveloperDTO developerDTO, RequestDTO requestDTO);

    Page<Developer> getAllDevelopers(Pageable pageable);

    Page<Developer> getAllDevelopers(Specification<Developer> spec, Pageable pageable);

	ResponseEntity<DeveloperPageDTO> getDevelopers(DeveloperSearchDTO developerSearchDTO);
	
	List<DeveloperDTO> convertDevelopersToDeveloperDTOs(List<Developer> developers, DeveloperConvertCriteriaDTO convertCriteria);

	DeveloperDTO getDeveloperDTOById(Integer developerId);







}





