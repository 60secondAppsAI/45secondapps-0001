package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Request;
import com.45secondapps.dto.RequestDTO;
import com.45secondapps.dto.RequestSearchDTO;
import com.45secondapps.dto.RequestPageDTO;
import com.45secondapps.dto.RequestConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RequestService extends GenericService<Request, Integer> {

	List<Request> findAll();

	ResultDTO addRequest(RequestDTO requestDTO, RequestDTO requestDTO);

	ResultDTO updateRequest(RequestDTO requestDTO, RequestDTO requestDTO);

    Page<Request> getAllRequests(Pageable pageable);

    Page<Request> getAllRequests(Specification<Request> spec, Pageable pageable);

	ResponseEntity<RequestPageDTO> getRequests(RequestSearchDTO requestSearchDTO);
	
	List<RequestDTO> convertRequestsToRequestDTOs(List<Request> requests, RequestConvertCriteriaDTO convertCriteria);

	RequestDTO getRequestDTOById(Integer requestId);







}





