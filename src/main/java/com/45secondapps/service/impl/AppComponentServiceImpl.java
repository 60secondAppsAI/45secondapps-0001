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
import com.45secondapps.dao.AppComponentDAO;
import com.45secondapps.domain.AppComponent;
import com.45secondapps.dto.AppComponentDTO;
import com.45secondapps.dto.AppComponentSearchDTO;
import com.45secondapps.dto.AppComponentPageDTO;
import com.45secondapps.dto.AppComponentConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.AppComponentService;
import com.45secondapps.util.ControllerUtils;





@Service
public class AppComponentServiceImpl extends GenericServiceImpl<AppComponent, Integer> implements AppComponentService {

    private final static Logger logger = LoggerFactory.getLogger(AppComponentServiceImpl.class);

	@Autowired
	AppComponentDAO appComponentDao;

	


	@Override
	public GenericDAO<AppComponent, Integer> getDAO() {
		return (GenericDAO<AppComponent, Integer>) appComponentDao;
	}
	
	public List<AppComponent> findAll () {
		List<AppComponent> appComponents = appComponentDao.findAll();
		
		return appComponents;	
		
	}

	public ResultDTO addAppComponent(AppComponentDTO appComponentDTO, RequestDTO requestDTO) {

		AppComponent appComponent = new AppComponent();

		appComponent.setAppComponentId(appComponentDTO.getAppComponentId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		appComponent = appComponentDao.save(appComponent);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<AppComponent> getAllAppComponents(Pageable pageable) {
		return appComponentDao.findAll(pageable);
	}

	public Page<AppComponent> getAllAppComponents(Specification<AppComponent> spec, Pageable pageable) {
		return appComponentDao.findAll(spec, pageable);
	}

	public ResponseEntity<AppComponentPageDTO> getAppComponents(AppComponentSearchDTO appComponentSearchDTO) {
	
			Integer appComponentId = appComponentSearchDTO.getAppComponentId(); 
 			String sortBy = appComponentSearchDTO.getSortBy();
			String sortOrder = appComponentSearchDTO.getSortOrder();
			String searchQuery = appComponentSearchDTO.getSearchQuery();
			Integer page = appComponentSearchDTO.getPage();
			Integer size = appComponentSearchDTO.getSize();

	        Specification<AppComponent> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, appComponentId, "appComponentId"); 
			

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

		Page<AppComponent> appComponents = this.getAllAppComponents(spec, pageable);
		
		//System.out.println(String.valueOf(appComponents.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(appComponents.getTotalPages()));
		
		List<AppComponent> appComponentsList = appComponents.getContent();
		
		AppComponentConvertCriteriaDTO convertCriteria = new AppComponentConvertCriteriaDTO();
		List<AppComponentDTO> appComponentDTOs = this.convertAppComponentsToAppComponentDTOs(appComponentsList,convertCriteria);
		
		AppComponentPageDTO appComponentPageDTO = new AppComponentPageDTO();
		appComponentPageDTO.setAppComponents(appComponentDTOs);
		appComponentPageDTO.setTotalElements(appComponents.getTotalElements());
		return ResponseEntity.ok(appComponentPageDTO);
	}

	public List<AppComponentDTO> convertAppComponentsToAppComponentDTOs(List<AppComponent> appComponents, AppComponentConvertCriteriaDTO convertCriteria) {
		
		List<AppComponentDTO> appComponentDTOs = new ArrayList<AppComponentDTO>();
		
		for (AppComponent appComponent : appComponents) {
			appComponentDTOs.add(convertAppComponentToAppComponentDTO(appComponent,convertCriteria));
		}
		
		return appComponentDTOs;

	}
	
	public AppComponentDTO convertAppComponentToAppComponentDTO(AppComponent appComponent, AppComponentConvertCriteriaDTO convertCriteria) {
		
		AppComponentDTO appComponentDTO = new AppComponentDTO();
		
		appComponentDTO.setAppComponentId(appComponent.getAppComponentId());

	

		
		return appComponentDTO;
	}

	public ResultDTO updateAppComponent(AppComponentDTO appComponentDTO, RequestDTO requestDTO) {
		
		AppComponent appComponent = appComponentDao.getById(appComponentDTO.getAppComponentId());

		appComponent.setAppComponentId(ControllerUtils.setValue(appComponent.getAppComponentId(), appComponentDTO.getAppComponentId()));



        appComponent = appComponentDao.save(appComponent);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AppComponentDTO getAppComponentDTOById(Integer appComponentId) {
	
		AppComponent appComponent = appComponentDao.getById(appComponentId);
			
		
		AppComponentConvertCriteriaDTO convertCriteria = new AppComponentConvertCriteriaDTO();
		return(this.convertAppComponentToAppComponentDTO(appComponent,convertCriteria));
	}







}
