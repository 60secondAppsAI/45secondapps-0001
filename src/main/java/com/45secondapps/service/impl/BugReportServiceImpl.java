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
import com.45secondapps.dao.BugReportDAO;
import com.45secondapps.domain.BugReport;
import com.45secondapps.dto.BugReportDTO;
import com.45secondapps.dto.BugReportSearchDTO;
import com.45secondapps.dto.BugReportPageDTO;
import com.45secondapps.dto.BugReportConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.BugReportService;
import com.45secondapps.util.ControllerUtils;





@Service
public class BugReportServiceImpl extends GenericServiceImpl<BugReport, Integer> implements BugReportService {

    private final static Logger logger = LoggerFactory.getLogger(BugReportServiceImpl.class);

	@Autowired
	BugReportDAO bugReportDao;

	


	@Override
	public GenericDAO<BugReport, Integer> getDAO() {
		return (GenericDAO<BugReport, Integer>) bugReportDao;
	}
	
	public List<BugReport> findAll () {
		List<BugReport> bugReports = bugReportDao.findAll();
		
		return bugReports;	
		
	}

	public ResultDTO addBugReport(BugReportDTO bugReportDTO, RequestDTO requestDTO) {

		BugReport bugReport = new BugReport();

		bugReport.setBugReportId(bugReportDTO.getBugReportId());


		bugReport.setBugDescription(bugReportDTO.getBugDescription());


		bugReport.setReportDate(bugReportDTO.getReportDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		bugReport = bugReportDao.save(bugReport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<BugReport> getAllBugReports(Pageable pageable) {
		return bugReportDao.findAll(pageable);
	}

	public Page<BugReport> getAllBugReports(Specification<BugReport> spec, Pageable pageable) {
		return bugReportDao.findAll(spec, pageable);
	}

	public ResponseEntity<BugReportPageDTO> getBugReports(BugReportSearchDTO bugReportSearchDTO) {
	
			Integer bugReportId = bugReportSearchDTO.getBugReportId(); 
 			String bugDescription = bugReportSearchDTO.getBugDescription(); 
   			String sortBy = bugReportSearchDTO.getSortBy();
			String sortOrder = bugReportSearchDTO.getSortOrder();
			String searchQuery = bugReportSearchDTO.getSearchQuery();
			Integer page = bugReportSearchDTO.getPage();
			Integer size = bugReportSearchDTO.getSize();

	        Specification<BugReport> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, bugReportId, "bugReportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, bugDescription, "bugDescription"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("bugDescription")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<BugReport> bugReports = this.getAllBugReports(spec, pageable);
		
		//System.out.println(String.valueOf(bugReports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(bugReports.getTotalPages()));
		
		List<BugReport> bugReportsList = bugReports.getContent();
		
		BugReportConvertCriteriaDTO convertCriteria = new BugReportConvertCriteriaDTO();
		List<BugReportDTO> bugReportDTOs = this.convertBugReportsToBugReportDTOs(bugReportsList,convertCriteria);
		
		BugReportPageDTO bugReportPageDTO = new BugReportPageDTO();
		bugReportPageDTO.setBugReports(bugReportDTOs);
		bugReportPageDTO.setTotalElements(bugReports.getTotalElements());
		return ResponseEntity.ok(bugReportPageDTO);
	}

	public List<BugReportDTO> convertBugReportsToBugReportDTOs(List<BugReport> bugReports, BugReportConvertCriteriaDTO convertCriteria) {
		
		List<BugReportDTO> bugReportDTOs = new ArrayList<BugReportDTO>();
		
		for (BugReport bugReport : bugReports) {
			bugReportDTOs.add(convertBugReportToBugReportDTO(bugReport,convertCriteria));
		}
		
		return bugReportDTOs;

	}
	
	public BugReportDTO convertBugReportToBugReportDTO(BugReport bugReport, BugReportConvertCriteriaDTO convertCriteria) {
		
		BugReportDTO bugReportDTO = new BugReportDTO();
		
		bugReportDTO.setBugReportId(bugReport.getBugReportId());

	
		bugReportDTO.setBugDescription(bugReport.getBugDescription());

	
		bugReportDTO.setReportDate(bugReport.getReportDate());

	

		
		return bugReportDTO;
	}

	public ResultDTO updateBugReport(BugReportDTO bugReportDTO, RequestDTO requestDTO) {
		
		BugReport bugReport = bugReportDao.getById(bugReportDTO.getBugReportId());

		bugReport.setBugReportId(ControllerUtils.setValue(bugReport.getBugReportId(), bugReportDTO.getBugReportId()));

		bugReport.setBugDescription(ControllerUtils.setValue(bugReport.getBugDescription(), bugReportDTO.getBugDescription()));

		bugReport.setReportDate(ControllerUtils.setValue(bugReport.getReportDate(), bugReportDTO.getReportDate()));



        bugReport = bugReportDao.save(bugReport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BugReportDTO getBugReportDTOById(Integer bugReportId) {
	
		BugReport bugReport = bugReportDao.getById(bugReportId);
			
		
		BugReportConvertCriteriaDTO convertCriteria = new BugReportConvertCriteriaDTO();
		return(this.convertBugReportToBugReportDTO(bugReport,convertCriteria));
	}







}
