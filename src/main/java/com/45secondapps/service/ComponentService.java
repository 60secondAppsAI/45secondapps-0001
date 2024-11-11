package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Component;
import com.45secondapps.dto.ComponentDTO;
import com.45secondapps.dto.ComponentSearchDTO;
import com.45secondapps.dto.ComponentPageDTO;
import com.45secondapps.dto.ComponentConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ComponentService extends GenericService<Component, Integer> {

	List<Component> findAll();

	ResultDTO addComponent(ComponentDTO componentDTO, RequestDTO requestDTO);

	ResultDTO updateComponent(ComponentDTO componentDTO, RequestDTO requestDTO);

    Page<Component> getAllComponents(Pageable pageable);

    Page<Component> getAllComponents(Specification<Component> spec, Pageable pageable);

	ResponseEntity<ComponentPageDTO> getComponents(ComponentSearchDTO componentSearchDTO);
	
	List<ComponentDTO> convertComponentsToComponentDTOs(List<Component> components, ComponentConvertCriteriaDTO convertCriteria);

	ComponentDTO getComponentDTOById(Integer componentId);







}





