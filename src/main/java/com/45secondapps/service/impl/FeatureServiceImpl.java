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
import com.45secondapps.dao.FeatureDAO;
import com.45secondapps.domain.Feature;
import com.45secondapps.dto.FeatureDTO;
import com.45secondapps.dto.FeatureSearchDTO;
import com.45secondapps.dto.FeaturePageDTO;
import com.45secondapps.dto.FeatureConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.FeatureService;
import com.45secondapps.util.ControllerUtils;





@Service
public class FeatureServiceImpl extends GenericServiceImpl<Feature, Integer> implements FeatureService {

    private final static Logger logger = LoggerFactory.getLogger(FeatureServiceImpl.class);

	@Autowired
	FeatureDAO featureDao;

	


	@Override
	public GenericDAO<Feature, Integer> getDAO() {
		return (GenericDAO<Feature, Integer>) featureDao;
	}
	
	public List<Feature> findAll () {
		List<Feature> features = featureDao.findAll();
		
		return features;	
		
	}

	public ResultDTO addFeature(FeatureDTO featureDTO, RequestDTO requestDTO) {

		Feature feature = new Feature();

		feature.setFeatureId(featureDTO.getFeatureId());


		feature.setFeatureName(featureDTO.getFeatureName());


		feature.setDescription(featureDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		feature = featureDao.save(feature);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Feature> getAllFeatures(Pageable pageable) {
		return featureDao.findAll(pageable);
	}

	public Page<Feature> getAllFeatures(Specification<Feature> spec, Pageable pageable) {
		return featureDao.findAll(spec, pageable);
	}

	public ResponseEntity<FeaturePageDTO> getFeatures(FeatureSearchDTO featureSearchDTO) {
	
			Integer featureId = featureSearchDTO.getFeatureId(); 
 			String featureName = featureSearchDTO.getFeatureName(); 
 			String description = featureSearchDTO.getDescription(); 
 			String sortBy = featureSearchDTO.getSortBy();
			String sortOrder = featureSearchDTO.getSortOrder();
			String searchQuery = featureSearchDTO.getSearchQuery();
			Integer page = featureSearchDTO.getPage();
			Integer size = featureSearchDTO.getSize();

	        Specification<Feature> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, featureId, "featureId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, featureName, "featureName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("featureName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Feature> features = this.getAllFeatures(spec, pageable);
		
		//System.out.println(String.valueOf(features.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(features.getTotalPages()));
		
		List<Feature> featuresList = features.getContent();
		
		FeatureConvertCriteriaDTO convertCriteria = new FeatureConvertCriteriaDTO();
		List<FeatureDTO> featureDTOs = this.convertFeaturesToFeatureDTOs(featuresList,convertCriteria);
		
		FeaturePageDTO featurePageDTO = new FeaturePageDTO();
		featurePageDTO.setFeatures(featureDTOs);
		featurePageDTO.setTotalElements(features.getTotalElements());
		return ResponseEntity.ok(featurePageDTO);
	}

	public List<FeatureDTO> convertFeaturesToFeatureDTOs(List<Feature> features, FeatureConvertCriteriaDTO convertCriteria) {
		
		List<FeatureDTO> featureDTOs = new ArrayList<FeatureDTO>();
		
		for (Feature feature : features) {
			featureDTOs.add(convertFeatureToFeatureDTO(feature,convertCriteria));
		}
		
		return featureDTOs;

	}
	
	public FeatureDTO convertFeatureToFeatureDTO(Feature feature, FeatureConvertCriteriaDTO convertCriteria) {
		
		FeatureDTO featureDTO = new FeatureDTO();
		
		featureDTO.setFeatureId(feature.getFeatureId());

	
		featureDTO.setFeatureName(feature.getFeatureName());

	
		featureDTO.setDescription(feature.getDescription());

	

		
		return featureDTO;
	}

	public ResultDTO updateFeature(FeatureDTO featureDTO, RequestDTO requestDTO) {
		
		Feature feature = featureDao.getById(featureDTO.getFeatureId());

		feature.setFeatureId(ControllerUtils.setValue(feature.getFeatureId(), featureDTO.getFeatureId()));

		feature.setFeatureName(ControllerUtils.setValue(feature.getFeatureName(), featureDTO.getFeatureName()));

		feature.setDescription(ControllerUtils.setValue(feature.getDescription(), featureDTO.getDescription()));



        feature = featureDao.save(feature);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FeatureDTO getFeatureDTOById(Integer featureId) {
	
		Feature feature = featureDao.getById(featureId);
			
		
		FeatureConvertCriteriaDTO convertCriteria = new FeatureConvertCriteriaDTO();
		return(this.convertFeatureToFeatureDTO(feature,convertCriteria));
	}







}
