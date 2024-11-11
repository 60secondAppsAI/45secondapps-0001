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
import com.45secondapps.dao.AppDAO;
import com.45secondapps.domain.App;
import com.45secondapps.dto.AppDTO;
import com.45secondapps.dto.AppSearchDTO;
import com.45secondapps.dto.AppPageDTO;
import com.45secondapps.dto.AppConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.AppService;
import com.45secondapps.util.ControllerUtils;





@Service
public class AppServiceImpl extends GenericServiceImpl<App, Integer> implements AppService {

    private final static Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

	@Autowired
	AppDAO appDao;

	


	@Override
	public GenericDAO<App, Integer> getDAO() {
		return (GenericDAO<App, Integer>) appDao;
	}
	
	public List<App> findAll () {
		List<App> apps = appDao.findAll();
		
		return apps;	
		
	}

	public ResultDTO addApp(AppDTO appDTO, RequestDTO requestDTO) {

		App app = new App();

		app.setAppId(appDTO.getAppId());


		app.setAppName(appDTO.getAppName());


		app.setCreatedDate(appDTO.getCreatedDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		app = appDao.save(app);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<App> getAllApps(Pageable pageable) {
		return appDao.findAll(pageable);
	}

	public Page<App> getAllApps(Specification<App> spec, Pageable pageable) {
		return appDao.findAll(spec, pageable);
	}

	public ResponseEntity<AppPageDTO> getApps(AppSearchDTO appSearchDTO) {
	
			Integer appId = appSearchDTO.getAppId(); 
 			String appName = appSearchDTO.getAppName(); 
   			String sortBy = appSearchDTO.getSortBy();
			String sortOrder = appSearchDTO.getSortOrder();
			String searchQuery = appSearchDTO.getSearchQuery();
			Integer page = appSearchDTO.getPage();
			Integer size = appSearchDTO.getSize();

	        Specification<App> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, appId, "appId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, appName, "appName"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("appName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<App> apps = this.getAllApps(spec, pageable);
		
		//System.out.println(String.valueOf(apps.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(apps.getTotalPages()));
		
		List<App> appsList = apps.getContent();
		
		AppConvertCriteriaDTO convertCriteria = new AppConvertCriteriaDTO();
		List<AppDTO> appDTOs = this.convertAppsToAppDTOs(appsList,convertCriteria);
		
		AppPageDTO appPageDTO = new AppPageDTO();
		appPageDTO.setApps(appDTOs);
		appPageDTO.setTotalElements(apps.getTotalElements());
		return ResponseEntity.ok(appPageDTO);
	}

	public List<AppDTO> convertAppsToAppDTOs(List<App> apps, AppConvertCriteriaDTO convertCriteria) {
		
		List<AppDTO> appDTOs = new ArrayList<AppDTO>();
		
		for (App app : apps) {
			appDTOs.add(convertAppToAppDTO(app,convertCriteria));
		}
		
		return appDTOs;

	}
	
	public AppDTO convertAppToAppDTO(App app, AppConvertCriteriaDTO convertCriteria) {
		
		AppDTO appDTO = new AppDTO();
		
		appDTO.setAppId(app.getAppId());

	
		appDTO.setAppName(app.getAppName());

	
		appDTO.setCreatedDate(app.getCreatedDate());

	

		
		return appDTO;
	}

	public ResultDTO updateApp(AppDTO appDTO, RequestDTO requestDTO) {
		
		App app = appDao.getById(appDTO.getAppId());

		app.setAppId(ControllerUtils.setValue(app.getAppId(), appDTO.getAppId()));

		app.setAppName(ControllerUtils.setValue(app.getAppName(), appDTO.getAppName()));

		app.setCreatedDate(ControllerUtils.setValue(app.getCreatedDate(), appDTO.getCreatedDate()));



        app = appDao.save(app);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AppDTO getAppDTOById(Integer appId) {
	
		App app = appDao.getById(appId);
			
		
		AppConvertCriteriaDTO convertCriteria = new AppConvertCriteriaDTO();
		return(this.convertAppToAppDTO(app,convertCriteria));
	}







}
