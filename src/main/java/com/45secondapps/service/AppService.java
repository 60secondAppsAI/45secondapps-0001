package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.App;
import com.45secondapps.dto.AppDTO;
import com.45secondapps.dto.AppSearchDTO;
import com.45secondapps.dto.AppPageDTO;
import com.45secondapps.dto.AppConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AppService extends GenericService<App, Integer> {

	List<App> findAll();

	ResultDTO addApp(AppDTO appDTO, RequestDTO requestDTO);

	ResultDTO updateApp(AppDTO appDTO, RequestDTO requestDTO);

    Page<App> getAllApps(Pageable pageable);

    Page<App> getAllApps(Specification<App> spec, Pageable pageable);

	ResponseEntity<AppPageDTO> getApps(AppSearchDTO appSearchDTO);
	
	List<AppDTO> convertAppsToAppDTOs(List<App> apps, AppConvertCriteriaDTO convertCriteria);

	AppDTO getAppDTOById(Integer appId);







}





