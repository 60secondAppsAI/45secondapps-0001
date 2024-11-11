package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Log;
import com.45secondapps.dto.LogDTO;
import com.45secondapps.dto.LogSearchDTO;
import com.45secondapps.dto.LogPageDTO;
import com.45secondapps.dto.LogConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LogService extends GenericService<Log, Integer> {

	List<Log> findAll();

	ResultDTO addLog(LogDTO logDTO, RequestDTO requestDTO);

	ResultDTO updateLog(LogDTO logDTO, RequestDTO requestDTO);

    Page<Log> getAllLogs(Pageable pageable);

    Page<Log> getAllLogs(Specification<Log> spec, Pageable pageable);

	ResponseEntity<LogPageDTO> getLogs(LogSearchDTO logSearchDTO);
	
	List<LogDTO> convertLogsToLogDTOs(List<Log> logs, LogConvertCriteriaDTO convertCriteria);

	LogDTO getLogDTOById(Integer logId);







}





