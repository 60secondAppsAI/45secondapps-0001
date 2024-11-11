package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Feature;
import com.45secondapps.dto.FeatureDTO;
import com.45secondapps.dto.FeatureSearchDTO;
import com.45secondapps.dto.FeaturePageDTO;
import com.45secondapps.dto.FeatureConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FeatureService extends GenericService<Feature, Integer> {

	List<Feature> findAll();

	ResultDTO addFeature(FeatureDTO featureDTO, RequestDTO requestDTO);

	ResultDTO updateFeature(FeatureDTO featureDTO, RequestDTO requestDTO);

    Page<Feature> getAllFeatures(Pageable pageable);

    Page<Feature> getAllFeatures(Specification<Feature> spec, Pageable pageable);

	ResponseEntity<FeaturePageDTO> getFeatures(FeatureSearchDTO featureSearchDTO);
	
	List<FeatureDTO> convertFeaturesToFeatureDTOs(List<Feature> features, FeatureConvertCriteriaDTO convertCriteria);

	FeatureDTO getFeatureDTOById(Integer featureId);







}





