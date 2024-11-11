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
import com.45secondapps.dao.ComponentDAO;
import com.45secondapps.domain.Component;
import com.45secondapps.dto.ComponentDTO;
import com.45secondapps.dto.ComponentSearchDTO;
import com.45secondapps.dto.ComponentPageDTO;
import com.45secondapps.dto.ComponentConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.ComponentService;
import com.45secondapps.util.ControllerUtils;





@Service
public class ComponentServiceImpl extends GenericServiceImpl<Component, Integer> implements ComponentService {

    private final static Logger logger = LoggerFactory.getLogger(ComponentServiceImpl.class);

	@Autowired
	ComponentDAO componentDao;

	


	@Override
	public GenericDAO<Component, Integer> getDAO() {
		return (GenericDAO<Component, Integer>) componentDao;
	}
	
	public List<Component> findAll () {
		List<Component> components = componentDao.findAll();
		
		return components;	
		
	}

	public ResultDTO addComponent(ComponentDTO componentDTO, RequestDTO requestDTO) {

		Component component = new Component();

		component.setComponentId(componentDTO.getComponentId());


		component.setName(componentDTO.getName());


		component.setType(componentDTO.getType());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		component = componentDao.save(component);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Component> getAllComponents(Pageable pageable) {
		return componentDao.findAll(pageable);
	}

	public Page<Component> getAllComponents(Specification<Component> spec, Pageable pageable) {
		return componentDao.findAll(spec, pageable);
	}

	public ResponseEntity<ComponentPageDTO> getComponents(ComponentSearchDTO componentSearchDTO) {
	
			Integer componentId = componentSearchDTO.getComponentId(); 
 			String name = componentSearchDTO.getName(); 
 			String type = componentSearchDTO.getType(); 
 			String sortBy = componentSearchDTO.getSortBy();
			String sortOrder = componentSearchDTO.getSortOrder();
			String searchQuery = componentSearchDTO.getSearchQuery();
			Integer page = componentSearchDTO.getPage();
			Integer size = componentSearchDTO.getSize();

	        Specification<Component> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, componentId, "componentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, type, "type"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("type")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Component> components = this.getAllComponents(spec, pageable);
		
		//System.out.println(String.valueOf(components.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(components.getTotalPages()));
		
		List<Component> componentsList = components.getContent();
		
		ComponentConvertCriteriaDTO convertCriteria = new ComponentConvertCriteriaDTO();
		List<ComponentDTO> componentDTOs = this.convertComponentsToComponentDTOs(componentsList,convertCriteria);
		
		ComponentPageDTO componentPageDTO = new ComponentPageDTO();
		componentPageDTO.setComponents(componentDTOs);
		componentPageDTO.setTotalElements(components.getTotalElements());
		return ResponseEntity.ok(componentPageDTO);
	}

	public List<ComponentDTO> convertComponentsToComponentDTOs(List<Component> components, ComponentConvertCriteriaDTO convertCriteria) {
		
		List<ComponentDTO> componentDTOs = new ArrayList<ComponentDTO>();
		
		for (Component component : components) {
			componentDTOs.add(convertComponentToComponentDTO(component,convertCriteria));
		}
		
		return componentDTOs;

	}
	
	public ComponentDTO convertComponentToComponentDTO(Component component, ComponentConvertCriteriaDTO convertCriteria) {
		
		ComponentDTO componentDTO = new ComponentDTO();
		
		componentDTO.setComponentId(component.getComponentId());

	
		componentDTO.setName(component.getName());

	
		componentDTO.setType(component.getType());

	

		
		return componentDTO;
	}

	public ResultDTO updateComponent(ComponentDTO componentDTO, RequestDTO requestDTO) {
		
		Component component = componentDao.getById(componentDTO.getComponentId());

		component.setComponentId(ControllerUtils.setValue(component.getComponentId(), componentDTO.getComponentId()));

		component.setName(ControllerUtils.setValue(component.getName(), componentDTO.getName()));

		component.setType(ControllerUtils.setValue(component.getType(), componentDTO.getType()));



        component = componentDao.save(component);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ComponentDTO getComponentDTOById(Integer componentId) {
	
		Component component = componentDao.getById(componentId);
			
		
		ComponentConvertCriteriaDTO convertCriteria = new ComponentConvertCriteriaDTO();
		return(this.convertComponentToComponentDTO(component,convertCriteria));
	}







}
