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
import com.45secondapps.dao.AppGeneratorDAO;
import com.45secondapps.domain.AppGenerator;
import com.45secondapps.dto.AppGeneratorDTO;
import com.45secondapps.dto.AppGeneratorSearchDTO;
import com.45secondapps.dto.AppGeneratorPageDTO;
import com.45secondapps.dto.AppGeneratorConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.AppGeneratorService;
import com.45secondapps.util.ControllerUtils;





@Service
public class AppGeneratorServiceImpl extends GenericServiceImpl<AppGenerator, Integer> implements AppGeneratorService {

    private final static Logger logger = LoggerFactory.getLogger(AppGeneratorServiceImpl.class);

	@Autowired
	AppGeneratorDAO appGeneratorDao;

	


	@Override
	public GenericDAO<AppGenerator, Integer> getDAO() {
		return (GenericDAO<AppGenerator, Integer>) appGeneratorDao;
	}
	
	public List<AppGenerator> findAll () {
		List<AppGenerator> appGenerators = appGeneratorDao.findAll();
		
		return appGenerators;	
		
	}

	public ResultDTO addAppGenerator(AppGeneratorDTO appGeneratorDTO, RequestDTO requestDTO) {

		AppGenerator appGenerator = new AppGenerator();

		appGenerator.setAppGeneratorId(appGeneratorDTO.getAppGeneratorId());


		appGenerator.setName(appGeneratorDTO.getName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		appGenerator = appGeneratorDao.save(appGenerator);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<AppGenerator> getAllAppGenerators(Pageable pageable) {
		return appGeneratorDao.findAll(pageable);
	}

	public Page<AppGenerator> getAllAppGenerators(Specification<AppGenerator> spec, Pageable pageable) {
		return appGeneratorDao.findAll(spec, pageable);
	}

	public ResponseEntity<AppGeneratorPageDTO> getAppGenerators(AppGeneratorSearchDTO appGeneratorSearchDTO) {
	
			Integer appGeneratorId = appGeneratorSearchDTO.getAppGeneratorId(); 
 			String name = appGeneratorSearchDTO.getName(); 
 			String sortBy = appGeneratorSearchDTO.getSortBy();
			String sortOrder = appGeneratorSearchDTO.getSortOrder();
			String searchQuery = appGeneratorSearchDTO.getSearchQuery();
			Integer page = appGeneratorSearchDTO.getPage();
			Integer size = appGeneratorSearchDTO.getSize();

	        Specification<AppGenerator> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, appGeneratorId, "appGeneratorId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<AppGenerator> appGenerators = this.getAllAppGenerators(spec, pageable);
		
		//System.out.println(String.valueOf(appGenerators.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(appGenerators.getTotalPages()));
		
		List<AppGenerator> appGeneratorsList = appGenerators.getContent();
		
		AppGeneratorConvertCriteriaDTO convertCriteria = new AppGeneratorConvertCriteriaDTO();
		List<AppGeneratorDTO> appGeneratorDTOs = this.convertAppGeneratorsToAppGeneratorDTOs(appGeneratorsList,convertCriteria);
		
		AppGeneratorPageDTO appGeneratorPageDTO = new AppGeneratorPageDTO();
		appGeneratorPageDTO.setAppGenerators(appGeneratorDTOs);
		appGeneratorPageDTO.setTotalElements(appGenerators.getTotalElements());
		return ResponseEntity.ok(appGeneratorPageDTO);
	}

	public List<AppGeneratorDTO> convertAppGeneratorsToAppGeneratorDTOs(List<AppGenerator> appGenerators, AppGeneratorConvertCriteriaDTO convertCriteria) {
		
		List<AppGeneratorDTO> appGeneratorDTOs = new ArrayList<AppGeneratorDTO>();
		
		for (AppGenerator appGenerator : appGenerators) {
			appGeneratorDTOs.add(convertAppGeneratorToAppGeneratorDTO(appGenerator,convertCriteria));
		}
		
		return appGeneratorDTOs;

	}
	
	public AppGeneratorDTO convertAppGeneratorToAppGeneratorDTO(AppGenerator appGenerator, AppGeneratorConvertCriteriaDTO convertCriteria) {
		
		AppGeneratorDTO appGeneratorDTO = new AppGeneratorDTO();
		
		appGeneratorDTO.setAppGeneratorId(appGenerator.getAppGeneratorId());

	
		appGeneratorDTO.setName(appGenerator.getName());

	

		
		return appGeneratorDTO;
	}

	public ResultDTO updateAppGenerator(AppGeneratorDTO appGeneratorDTO, RequestDTO requestDTO) {
		
		AppGenerator appGenerator = appGeneratorDao.getById(appGeneratorDTO.getAppGeneratorId());

		appGenerator.setAppGeneratorId(ControllerUtils.setValue(appGenerator.getAppGeneratorId(), appGeneratorDTO.getAppGeneratorId()));

		appGenerator.setName(ControllerUtils.setValue(appGenerator.getName(), appGeneratorDTO.getName()));



        appGenerator = appGeneratorDao.save(appGenerator);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AppGeneratorDTO getAppGeneratorDTOById(Integer appGeneratorId) {
	
		AppGenerator appGenerator = appGeneratorDao.getById(appGeneratorId);
			
		
		AppGeneratorConvertCriteriaDTO convertCriteria = new AppGeneratorConvertCriteriaDTO();
		return(this.convertAppGeneratorToAppGeneratorDTO(appGenerator,convertCriteria));
	}







}
