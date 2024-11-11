package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.BugReport;
import com.45secondapps.dto.BugReportDTO;
import com.45secondapps.dto.BugReportSearchDTO;
import com.45secondapps.dto.BugReportPageDTO;
import com.45secondapps.dto.BugReportConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BugReportService extends GenericService<BugReport, Integer> {

	List<BugReport> findAll();

	ResultDTO addBugReport(BugReportDTO bugReportDTO, RequestDTO requestDTO);

	ResultDTO updateBugReport(BugReportDTO bugReportDTO, RequestDTO requestDTO);

    Page<BugReport> getAllBugReports(Pageable pageable);

    Page<BugReport> getAllBugReports(Specification<BugReport> spec, Pageable pageable);

	ResponseEntity<BugReportPageDTO> getBugReports(BugReportSearchDTO bugReportSearchDTO);
	
	List<BugReportDTO> convertBugReportsToBugReportDTOs(List<BugReport> bugReports, BugReportConvertCriteriaDTO convertCriteria);

	BugReportDTO getBugReportDTOById(Integer bugReportId);







}





