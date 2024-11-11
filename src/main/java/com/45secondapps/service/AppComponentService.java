package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.AppComponent;
import com.45secondapps.dto.AppComponentDTO;
import com.45secondapps.dto.AppComponentSearchDTO;
import com.45secondapps.dto.AppComponentPageDTO;
import com.45secondapps.dto.AppComponentConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AppComponentService extends GenericService<AppComponent, Integer> {

	List<AppComponent> findAll();

	ResultDTO addAppComponent(AppComponentDTO appComponentDTO, RequestDTO requestDTO);

	ResultDTO updateAppComponent(AppComponentDTO appComponentDTO, RequestDTO requestDTO);

    Page<AppComponent> getAllAppComponents(Pageable pageable);

    Page<AppComponent> getAllAppComponents(Specification<AppComponent> spec, Pageable pageable);

	ResponseEntity<AppComponentPageDTO> getAppComponents(AppComponentSearchDTO appComponentSearchDTO);
	
	List<AppComponentDTO> convertAppComponentsToAppComponentDTOs(List<AppComponent> appComponents, AppComponentConvertCriteriaDTO convertCriteria);

	AppComponentDTO getAppComponentDTOById(Integer appComponentId);







}





