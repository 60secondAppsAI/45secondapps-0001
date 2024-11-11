package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.AppGenerator;
import com.45secondapps.dto.AppGeneratorDTO;
import com.45secondapps.dto.AppGeneratorSearchDTO;
import com.45secondapps.dto.AppGeneratorPageDTO;
import com.45secondapps.dto.AppGeneratorConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AppGeneratorService extends GenericService<AppGenerator, Integer> {

	List<AppGenerator> findAll();

	ResultDTO addAppGenerator(AppGeneratorDTO appGeneratorDTO, RequestDTO requestDTO);

	ResultDTO updateAppGenerator(AppGeneratorDTO appGeneratorDTO, RequestDTO requestDTO);

    Page<AppGenerator> getAllAppGenerators(Pageable pageable);

    Page<AppGenerator> getAllAppGenerators(Specification<AppGenerator> spec, Pageable pageable);

	ResponseEntity<AppGeneratorPageDTO> getAppGenerators(AppGeneratorSearchDTO appGeneratorSearchDTO);
	
	List<AppGeneratorDTO> convertAppGeneratorsToAppGeneratorDTOs(List<AppGenerator> appGenerators, AppGeneratorConvertCriteriaDTO convertCriteria);

	AppGeneratorDTO getAppGeneratorDTOById(Integer appGeneratorId);







}





