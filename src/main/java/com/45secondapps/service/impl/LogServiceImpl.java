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
import com.45secondapps.dao.LogDAO;
import com.45secondapps.domain.Log;
import com.45secondapps.dto.LogDTO;
import com.45secondapps.dto.LogSearchDTO;
import com.45secondapps.dto.LogPageDTO;
import com.45secondapps.dto.LogConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.LogService;
import com.45secondapps.util.ControllerUtils;





@Service
public class LogServiceImpl extends GenericServiceImpl<Log, Integer> implements LogService {

    private final static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

	@Autowired
	LogDAO logDao;

	


	@Override
	public GenericDAO<Log, Integer> getDAO() {
		return (GenericDAO<Log, Integer>) logDao;
	}
	
	public List<Log> findAll () {
		List<Log> logs = logDao.findAll();
		
		return logs;	
		
	}

	public ResultDTO addLog(LogDTO logDTO, RequestDTO requestDTO) {

		Log log = new Log();

		log.setLogId(logDTO.getLogId());


		log.setAction(logDTO.getAction());


		log.setTimestamp(logDTO.getTimestamp());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		log = logDao.save(log);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Log> getAllLogs(Pageable pageable) {
		return logDao.findAll(pageable);
	}

	public Page<Log> getAllLogs(Specification<Log> spec, Pageable pageable) {
		return logDao.findAll(spec, pageable);
	}

	public ResponseEntity<LogPageDTO> getLogs(LogSearchDTO logSearchDTO) {
	
			Integer logId = logSearchDTO.getLogId(); 
 			String action = logSearchDTO.getAction(); 
   			String sortBy = logSearchDTO.getSortBy();
			String sortOrder = logSearchDTO.getSortOrder();
			String searchQuery = logSearchDTO.getSearchQuery();
			Integer page = logSearchDTO.getPage();
			Integer size = logSearchDTO.getSize();

	        Specification<Log> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, logId, "logId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, action, "action"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("action")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Log> logs = this.getAllLogs(spec, pageable);
		
		//System.out.println(String.valueOf(logs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(logs.getTotalPages()));
		
		List<Log> logsList = logs.getContent();
		
		LogConvertCriteriaDTO convertCriteria = new LogConvertCriteriaDTO();
		List<LogDTO> logDTOs = this.convertLogsToLogDTOs(logsList,convertCriteria);
		
		LogPageDTO logPageDTO = new LogPageDTO();
		logPageDTO.setLogs(logDTOs);
		logPageDTO.setTotalElements(logs.getTotalElements());
		return ResponseEntity.ok(logPageDTO);
	}

	public List<LogDTO> convertLogsToLogDTOs(List<Log> logs, LogConvertCriteriaDTO convertCriteria) {
		
		List<LogDTO> logDTOs = new ArrayList<LogDTO>();
		
		for (Log log : logs) {
			logDTOs.add(convertLogToLogDTO(log,convertCriteria));
		}
		
		return logDTOs;

	}
	
	public LogDTO convertLogToLogDTO(Log log, LogConvertCriteriaDTO convertCriteria) {
		
		LogDTO logDTO = new LogDTO();
		
		logDTO.setLogId(log.getLogId());

	
		logDTO.setAction(log.getAction());

	
		logDTO.setTimestamp(log.getTimestamp());

	

		
		return logDTO;
	}

	public ResultDTO updateLog(LogDTO logDTO, RequestDTO requestDTO) {
		
		Log log = logDao.getById(logDTO.getLogId());

		log.setLogId(ControllerUtils.setValue(log.getLogId(), logDTO.getLogId()));

		log.setAction(ControllerUtils.setValue(log.getAction(), logDTO.getAction()));

		log.setTimestamp(ControllerUtils.setValue(log.getTimestamp(), logDTO.getTimestamp()));



        log = logDao.save(log);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public LogDTO getLogDTOById(Integer logId) {
	
		Log log = logDao.getById(logId);
			
		
		LogConvertCriteriaDTO convertCriteria = new LogConvertCriteriaDTO();
		return(this.convertLogToLogDTO(log,convertCriteria));
	}







}
