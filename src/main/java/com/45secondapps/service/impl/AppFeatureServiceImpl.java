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
import com.45secondapps.dao.AppFeatureDAO;
import com.45secondapps.domain.AppFeature;
import com.45secondapps.dto.AppFeatureDTO;
import com.45secondapps.dto.AppFeatureSearchDTO;
import com.45secondapps.dto.AppFeaturePageDTO;
import com.45secondapps.dto.AppFeatureConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.AppFeatureService;
import com.45secondapps.util.ControllerUtils;





@Service
public class AppFeatureServiceImpl extends GenericServiceImpl<AppFeature, Integer> implements AppFeatureService {

    private final static Logger logger = LoggerFactory.getLogger(AppFeatureServiceImpl.class);

	@Autowired
	AppFeatureDAO appFeatureDao;

	


	@Override
	public GenericDAO<AppFeature, Integer> getDAO() {
		return (GenericDAO<AppFeature, Integer>) appFeatureDao;
	}
	
	public List<AppFeature> findAll () {
		List<AppFeature> appFeatures = appFeatureDao.findAll();
		
		return appFeatures;	
		
	}

	public ResultDTO addAppFeature(AppFeatureDTO appFeatureDTO, RequestDTO requestDTO) {

		AppFeature appFeature = new AppFeature();

		appFeature.setAppFeatureId(appFeatureDTO.getAppFeatureId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		appFeature = appFeatureDao.save(appFeature);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<AppFeature> getAllAppFeatures(Pageable pageable) {
		return appFeatureDao.findAll(pageable);
	}

	public Page<AppFeature> getAllAppFeatures(Specification<AppFeature> spec, Pageable pageable) {
		return appFeatureDao.findAll(spec, pageable);
	}

	public ResponseEntity<AppFeaturePageDTO> getAppFeatures(AppFeatureSearchDTO appFeatureSearchDTO) {
	
			Integer appFeatureId = appFeatureSearchDTO.getAppFeatureId(); 
 			String sortBy = appFeatureSearchDTO.getSortBy();
			String sortOrder = appFeatureSearchDTO.getSortOrder();
			String searchQuery = appFeatureSearchDTO.getSearchQuery();
			Integer page = appFeatureSearchDTO.getPage();
			Integer size = appFeatureSearchDTO.getSize();

	        Specification<AppFeature> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, appFeatureId, "appFeatureId"); 
			

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

		Page<AppFeature> appFeatures = this.getAllAppFeatures(spec, pageable);
		
		//System.out.println(String.valueOf(appFeatures.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(appFeatures.getTotalPages()));
		
		List<AppFeature> appFeaturesList = appFeatures.getContent();
		
		AppFeatureConvertCriteriaDTO convertCriteria = new AppFeatureConvertCriteriaDTO();
		List<AppFeatureDTO> appFeatureDTOs = this.convertAppFeaturesToAppFeatureDTOs(appFeaturesList,convertCriteria);
		
		AppFeaturePageDTO appFeaturePageDTO = new AppFeaturePageDTO();
		appFeaturePageDTO.setAppFeatures(appFeatureDTOs);
		appFeaturePageDTO.setTotalElements(appFeatures.getTotalElements());
		return ResponseEntity.ok(appFeaturePageDTO);
	}

	public List<AppFeatureDTO> convertAppFeaturesToAppFeatureDTOs(List<AppFeature> appFeatures, AppFeatureConvertCriteriaDTO convertCriteria) {
		
		List<AppFeatureDTO> appFeatureDTOs = new ArrayList<AppFeatureDTO>();
		
		for (AppFeature appFeature : appFeatures) {
			appFeatureDTOs.add(convertAppFeatureToAppFeatureDTO(appFeature,convertCriteria));
		}
		
		return appFeatureDTOs;

	}
	
	public AppFeatureDTO convertAppFeatureToAppFeatureDTO(AppFeature appFeature, AppFeatureConvertCriteriaDTO convertCriteria) {
		
		AppFeatureDTO appFeatureDTO = new AppFeatureDTO();
		
		appFeatureDTO.setAppFeatureId(appFeature.getAppFeatureId());

	

		
		return appFeatureDTO;
	}

	public ResultDTO updateAppFeature(AppFeatureDTO appFeatureDTO, RequestDTO requestDTO) {
		
		AppFeature appFeature = appFeatureDao.getById(appFeatureDTO.getAppFeatureId());

		appFeature.setAppFeatureId(ControllerUtils.setValue(appFeature.getAppFeatureId(), appFeatureDTO.getAppFeatureId()));



        appFeature = appFeatureDao.save(appFeature);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AppFeatureDTO getAppFeatureDTOById(Integer appFeatureId) {
	
		AppFeature appFeature = appFeatureDao.getById(appFeatureId);
			
		
		AppFeatureConvertCriteriaDTO convertCriteria = new AppFeatureConvertCriteriaDTO();
		return(this.convertAppFeatureToAppFeatureDTO(appFeature,convertCriteria));
	}







}
