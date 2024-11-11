package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.AppFeature;
import com.45secondapps.dto.AppFeatureDTO;
import com.45secondapps.dto.AppFeatureSearchDTO;
import com.45secondapps.dto.AppFeaturePageDTO;
import com.45secondapps.dto.AppFeatureConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AppFeatureService extends GenericService<AppFeature, Integer> {

	List<AppFeature> findAll();

	ResultDTO addAppFeature(AppFeatureDTO appFeatureDTO, RequestDTO requestDTO);

	ResultDTO updateAppFeature(AppFeatureDTO appFeatureDTO, RequestDTO requestDTO);

    Page<AppFeature> getAllAppFeatures(Pageable pageable);

    Page<AppFeature> getAllAppFeatures(Specification<AppFeature> spec, Pageable pageable);

	ResponseEntity<AppFeaturePageDTO> getAppFeatures(AppFeatureSearchDTO appFeatureSearchDTO);
	
	List<AppFeatureDTO> convertAppFeaturesToAppFeatureDTOs(List<AppFeature> appFeatures, AppFeatureConvertCriteriaDTO convertCriteria);

	AppFeatureDTO getAppFeatureDTOById(Integer appFeatureId);







}





