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
import com.45secondapps.dao.RequestDAO;
import com.45secondapps.domain.Request;
import com.45secondapps.dto.RequestDTO;
import com.45secondapps.dto.RequestSearchDTO;
import com.45secondapps.dto.RequestPageDTO;
import com.45secondapps.dto.RequestConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.RequestService;
import com.45secondapps.util.ControllerUtils;





@Service
public class RequestServiceImpl extends GenericServiceImpl<Request, Integer> implements RequestService {

    private final static Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

	@Autowired
	RequestDAO requestDao;

	


	@Override
	public GenericDAO<Request, Integer> getDAO() {
		return (GenericDAO<Request, Integer>) requestDao;
	}
	
	public List<Request> findAll () {
		List<Request> requests = requestDao.findAll();
		
		return requests;	
		
	}

	public ResultDTO addRequest(RequestDTO requestDTO, RequestDTO requestDTO) {

		Request request = new Request();

		request.setRequestId(requestDTO.getRequestId());


		request.setGenerationTime(requestDTO.getGenerationTime());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		request = requestDao.save(request);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Request> getAllRequests(Pageable pageable) {
		return requestDao.findAll(pageable);
	}

	public Page<Request> getAllRequests(Specification<Request> spec, Pageable pageable) {
		return requestDao.findAll(spec, pageable);
	}

	public ResponseEntity<RequestPageDTO> getRequests(RequestSearchDTO requestSearchDTO) {
	
			Integer requestId = requestSearchDTO.getRequestId(); 
   			String sortBy = requestSearchDTO.getSortBy();
			String sortOrder = requestSearchDTO.getSortOrder();
			String searchQuery = requestSearchDTO.getSearchQuery();
			Integer page = requestSearchDTO.getPage();
			Integer size = requestSearchDTO.getSize();

	        Specification<Request> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, requestId, "requestId"); 
			
 			

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

		Page<Request> requests = this.getAllRequests(spec, pageable);
		
		//System.out.println(String.valueOf(requests.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(requests.getTotalPages()));
		
		List<Request> requestsList = requests.getContent();
		
		RequestConvertCriteriaDTO convertCriteria = new RequestConvertCriteriaDTO();
		List<RequestDTO> requestDTOs = this.convertRequestsToRequestDTOs(requestsList,convertCriteria);
		
		RequestPageDTO requestPageDTO = new RequestPageDTO();
		requestPageDTO.setRequests(requestDTOs);
		requestPageDTO.setTotalElements(requests.getTotalElements());
		return ResponseEntity.ok(requestPageDTO);
	}

	public List<RequestDTO> convertRequestsToRequestDTOs(List<Request> requests, RequestConvertCriteriaDTO convertCriteria) {
		
		List<RequestDTO> requestDTOs = new ArrayList<RequestDTO>();
		
		for (Request request : requests) {
			requestDTOs.add(convertRequestToRequestDTO(request,convertCriteria));
		}
		
		return requestDTOs;

	}
	
	public RequestDTO convertRequestToRequestDTO(Request request, RequestConvertCriteriaDTO convertCriteria) {
		
		RequestDTO requestDTO = new RequestDTO();
		
		requestDTO.setRequestId(request.getRequestId());

	
		requestDTO.setGenerationTime(request.getGenerationTime());

	

		
		return requestDTO;
	}

	public ResultDTO updateRequest(RequestDTO requestDTO, RequestDTO requestDTO) {
		
		Request request = requestDao.getById(requestDTO.getRequestId());

		request.setRequestId(ControllerUtils.setValue(request.getRequestId(), requestDTO.getRequestId()));

		request.setGenerationTime(ControllerUtils.setValue(request.getGenerationTime(), requestDTO.getGenerationTime()));



        request = requestDao.save(request);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RequestDTO getRequestDTOById(Integer requestId) {
	
		Request request = requestDao.getById(requestId);
			
		
		RequestConvertCriteriaDTO convertCriteria = new RequestConvertCriteriaDTO();
		return(this.convertRequestToRequestDTO(request,convertCriteria));
	}







}
