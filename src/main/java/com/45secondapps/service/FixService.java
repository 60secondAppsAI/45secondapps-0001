package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Fix;
import com.45secondapps.dto.FixDTO;
import com.45secondapps.dto.FixSearchDTO;
import com.45secondapps.dto.FixPageDTO;
import com.45secondapps.dto.FixConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FixService extends GenericService<Fix, Integer> {

	List<Fix> findAll();

	ResultDTO addFix(FixDTO fixDTO, RequestDTO requestDTO);

	ResultDTO updateFix(FixDTO fixDTO, RequestDTO requestDTO);

    Page<Fix> getAllFixs(Pageable pageable);

    Page<Fix> getAllFixs(Specification<Fix> spec, Pageable pageable);

	ResponseEntity<FixPageDTO> getFixs(FixSearchDTO fixSearchDTO);
	
	List<FixDTO> convertFixsToFixDTOs(List<Fix> fixs, FixConvertCriteriaDTO convertCriteria);

	FixDTO getFixDTOById(Integer fixId);







}





