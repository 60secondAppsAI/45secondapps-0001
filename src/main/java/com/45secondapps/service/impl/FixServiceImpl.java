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
import com.45secondapps.dao.FixDAO;
import com.45secondapps.domain.Fix;
import com.45secondapps.dto.FixDTO;
import com.45secondapps.dto.FixSearchDTO;
import com.45secondapps.dto.FixPageDTO;
import com.45secondapps.dto.FixConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.FixService;
import com.45secondapps.util.ControllerUtils;





@Service
public class FixServiceImpl extends GenericServiceImpl<Fix, Integer> implements FixService {

    private final static Logger logger = LoggerFactory.getLogger(FixServiceImpl.class);

	@Autowired
	FixDAO fixDao;

	


	@Override
	public GenericDAO<Fix, Integer> getDAO() {
		return (GenericDAO<Fix, Integer>) fixDao;
	}
	
	public List<Fix> findAll () {
		List<Fix> fixs = fixDao.findAll();
		
		return fixs;	
		
	}

	public ResultDTO addFix(FixDTO fixDTO, RequestDTO requestDTO) {

		Fix fix = new Fix();

		fix.setFixId(fixDTO.getFixId());


		fix.setFixDate(fixDTO.getFixDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		fix = fixDao.save(fix);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Fix> getAllFixs(Pageable pageable) {
		return fixDao.findAll(pageable);
	}

	public Page<Fix> getAllFixs(Specification<Fix> spec, Pageable pageable) {
		return fixDao.findAll(spec, pageable);
	}

	public ResponseEntity<FixPageDTO> getFixs(FixSearchDTO fixSearchDTO) {
	
			Integer fixId = fixSearchDTO.getFixId(); 
   			String sortBy = fixSearchDTO.getSortBy();
			String sortOrder = fixSearchDTO.getSortOrder();
			String searchQuery = fixSearchDTO.getSearchQuery();
			Integer page = fixSearchDTO.getPage();
			Integer size = fixSearchDTO.getSize();

	        Specification<Fix> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, fixId, "fixId"); 
			
 			

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

		Page<Fix> fixs = this.getAllFixs(spec, pageable);
		
		//System.out.println(String.valueOf(fixs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(fixs.getTotalPages()));
		
		List<Fix> fixsList = fixs.getContent();
		
		FixConvertCriteriaDTO convertCriteria = new FixConvertCriteriaDTO();
		List<FixDTO> fixDTOs = this.convertFixsToFixDTOs(fixsList,convertCriteria);
		
		FixPageDTO fixPageDTO = new FixPageDTO();
		fixPageDTO.setFixs(fixDTOs);
		fixPageDTO.setTotalElements(fixs.getTotalElements());
		return ResponseEntity.ok(fixPageDTO);
	}

	public List<FixDTO> convertFixsToFixDTOs(List<Fix> fixs, FixConvertCriteriaDTO convertCriteria) {
		
		List<FixDTO> fixDTOs = new ArrayList<FixDTO>();
		
		for (Fix fix : fixs) {
			fixDTOs.add(convertFixToFixDTO(fix,convertCriteria));
		}
		
		return fixDTOs;

	}
	
	public FixDTO convertFixToFixDTO(Fix fix, FixConvertCriteriaDTO convertCriteria) {
		
		FixDTO fixDTO = new FixDTO();
		
		fixDTO.setFixId(fix.getFixId());

	
		fixDTO.setFixDate(fix.getFixDate());

	

		
		return fixDTO;
	}

	public ResultDTO updateFix(FixDTO fixDTO, RequestDTO requestDTO) {
		
		Fix fix = fixDao.getById(fixDTO.getFixId());

		fix.setFixId(ControllerUtils.setValue(fix.getFixId(), fixDTO.getFixId()));

		fix.setFixDate(ControllerUtils.setValue(fix.getFixDate(), fixDTO.getFixDate()));



        fix = fixDao.save(fix);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FixDTO getFixDTOById(Integer fixId) {
	
		Fix fix = fixDao.getById(fixId);
			
		
		FixConvertCriteriaDTO convertCriteria = new FixConvertCriteriaDTO();
		return(this.convertFixToFixDTO(fix,convertCriteria));
	}







}
