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
import com.45secondapps.dao.DeveloperDAO;
import com.45secondapps.domain.Developer;
import com.45secondapps.dto.DeveloperDTO;
import com.45secondapps.dto.DeveloperSearchDTO;
import com.45secondapps.dto.DeveloperPageDTO;
import com.45secondapps.dto.DeveloperConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.DeveloperService;
import com.45secondapps.util.ControllerUtils;





@Service
public class DeveloperServiceImpl extends GenericServiceImpl<Developer, Integer> implements DeveloperService {

    private final static Logger logger = LoggerFactory.getLogger(DeveloperServiceImpl.class);

	@Autowired
	DeveloperDAO developerDao;

	


	@Override
	public GenericDAO<Developer, Integer> getDAO() {
		return (GenericDAO<Developer, Integer>) developerDao;
	}
	
	public List<Developer> findAll () {
		List<Developer> developers = developerDao.findAll();
		
		return developers;	
		
	}

	public ResultDTO addDeveloper(DeveloperDTO developerDTO, RequestDTO requestDTO) {

		Developer developer = new Developer();

		developer.setDeveloperId(developerDTO.getDeveloperId());


		developer.setName(developerDTO.getName());


		developer.setExpertise(developerDTO.getExpertise());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		developer = developerDao.save(developer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Developer> getAllDevelopers(Pageable pageable) {
		return developerDao.findAll(pageable);
	}

	public Page<Developer> getAllDevelopers(Specification<Developer> spec, Pageable pageable) {
		return developerDao.findAll(spec, pageable);
	}

	public ResponseEntity<DeveloperPageDTO> getDevelopers(DeveloperSearchDTO developerSearchDTO) {
	
			Integer developerId = developerSearchDTO.getDeveloperId(); 
 			String name = developerSearchDTO.getName(); 
 			String expertise = developerSearchDTO.getExpertise(); 
 			String sortBy = developerSearchDTO.getSortBy();
			String sortOrder = developerSearchDTO.getSortOrder();
			String searchQuery = developerSearchDTO.getSearchQuery();
			Integer page = developerSearchDTO.getPage();
			Integer size = developerSearchDTO.getSize();

	        Specification<Developer> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, developerId, "developerId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, expertise, "expertise"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("expertise")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Developer> developers = this.getAllDevelopers(spec, pageable);
		
		//System.out.println(String.valueOf(developers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(developers.getTotalPages()));
		
		List<Developer> developersList = developers.getContent();
		
		DeveloperConvertCriteriaDTO convertCriteria = new DeveloperConvertCriteriaDTO();
		List<DeveloperDTO> developerDTOs = this.convertDevelopersToDeveloperDTOs(developersList,convertCriteria);
		
		DeveloperPageDTO developerPageDTO = new DeveloperPageDTO();
		developerPageDTO.setDevelopers(developerDTOs);
		developerPageDTO.setTotalElements(developers.getTotalElements());
		return ResponseEntity.ok(developerPageDTO);
	}

	public List<DeveloperDTO> convertDevelopersToDeveloperDTOs(List<Developer> developers, DeveloperConvertCriteriaDTO convertCriteria) {
		
		List<DeveloperDTO> developerDTOs = new ArrayList<DeveloperDTO>();
		
		for (Developer developer : developers) {
			developerDTOs.add(convertDeveloperToDeveloperDTO(developer,convertCriteria));
		}
		
		return developerDTOs;

	}
	
	public DeveloperDTO convertDeveloperToDeveloperDTO(Developer developer, DeveloperConvertCriteriaDTO convertCriteria) {
		
		DeveloperDTO developerDTO = new DeveloperDTO();
		
		developerDTO.setDeveloperId(developer.getDeveloperId());

	
		developerDTO.setName(developer.getName());

	
		developerDTO.setExpertise(developer.getExpertise());

	

		
		return developerDTO;
	}

	public ResultDTO updateDeveloper(DeveloperDTO developerDTO, RequestDTO requestDTO) {
		
		Developer developer = developerDao.getById(developerDTO.getDeveloperId());

		developer.setDeveloperId(ControllerUtils.setValue(developer.getDeveloperId(), developerDTO.getDeveloperId()));

		developer.setName(ControllerUtils.setValue(developer.getName(), developerDTO.getName()));

		developer.setExpertise(ControllerUtils.setValue(developer.getExpertise(), developerDTO.getExpertise()));



        developer = developerDao.save(developer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DeveloperDTO getDeveloperDTOById(Integer developerId) {
	
		Developer developer = developerDao.getById(developerId);
			
		
		DeveloperConvertCriteriaDTO convertCriteria = new DeveloperConvertCriteriaDTO();
		return(this.convertDeveloperToDeveloperDTO(developer,convertCriteria));
	}







}
