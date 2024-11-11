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
import com.45secondapps.dao.BillingDAO;
import com.45secondapps.domain.Billing;
import com.45secondapps.dto.BillingDTO;
import com.45secondapps.dto.BillingSearchDTO;
import com.45secondapps.dto.BillingPageDTO;
import com.45secondapps.dto.BillingConvertCriteriaDTO;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import com.45secondapps.service.BillingService;
import com.45secondapps.util.ControllerUtils;





@Service
public class BillingServiceImpl extends GenericServiceImpl<Billing, Integer> implements BillingService {

    private final static Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);

	@Autowired
	BillingDAO billingDao;

	


	@Override
	public GenericDAO<Billing, Integer> getDAO() {
		return (GenericDAO<Billing, Integer>) billingDao;
	}
	
	public List<Billing> findAll () {
		List<Billing> billings = billingDao.findAll();
		
		return billings;	
		
	}

	public ResultDTO addBilling(BillingDTO billingDTO, RequestDTO requestDTO) {

		Billing billing = new Billing();

		billing.setBillingId(billingDTO.getBillingId());


		billing.setAmount(billingDTO.getAmount());


		billing.setBillingDate(billingDTO.getBillingDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		billing = billingDao.save(billing);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Billing> getAllBillings(Pageable pageable) {
		return billingDao.findAll(pageable);
	}

	public Page<Billing> getAllBillings(Specification<Billing> spec, Pageable pageable) {
		return billingDao.findAll(spec, pageable);
	}

	public ResponseEntity<BillingPageDTO> getBillings(BillingSearchDTO billingSearchDTO) {
	
			Integer billingId = billingSearchDTO.getBillingId(); 
    			String sortBy = billingSearchDTO.getSortBy();
			String sortOrder = billingSearchDTO.getSortOrder();
			String searchQuery = billingSearchDTO.getSearchQuery();
			Integer page = billingSearchDTO.getPage();
			Integer size = billingSearchDTO.getSize();

	        Specification<Billing> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, billingId, "billingId"); 
			
			
 			

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

		Page<Billing> billings = this.getAllBillings(spec, pageable);
		
		//System.out.println(String.valueOf(billings.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(billings.getTotalPages()));
		
		List<Billing> billingsList = billings.getContent();
		
		BillingConvertCriteriaDTO convertCriteria = new BillingConvertCriteriaDTO();
		List<BillingDTO> billingDTOs = this.convertBillingsToBillingDTOs(billingsList,convertCriteria);
		
		BillingPageDTO billingPageDTO = new BillingPageDTO();
		billingPageDTO.setBillings(billingDTOs);
		billingPageDTO.setTotalElements(billings.getTotalElements());
		return ResponseEntity.ok(billingPageDTO);
	}

	public List<BillingDTO> convertBillingsToBillingDTOs(List<Billing> billings, BillingConvertCriteriaDTO convertCriteria) {
		
		List<BillingDTO> billingDTOs = new ArrayList<BillingDTO>();
		
		for (Billing billing : billings) {
			billingDTOs.add(convertBillingToBillingDTO(billing,convertCriteria));
		}
		
		return billingDTOs;

	}
	
	public BillingDTO convertBillingToBillingDTO(Billing billing, BillingConvertCriteriaDTO convertCriteria) {
		
		BillingDTO billingDTO = new BillingDTO();
		
		billingDTO.setBillingId(billing.getBillingId());

	
		billingDTO.setAmount(billing.getAmount());

	
		billingDTO.setBillingDate(billing.getBillingDate());

	

		
		return billingDTO;
	}

	public ResultDTO updateBilling(BillingDTO billingDTO, RequestDTO requestDTO) {
		
		Billing billing = billingDao.getById(billingDTO.getBillingId());

		billing.setBillingId(ControllerUtils.setValue(billing.getBillingId(), billingDTO.getBillingId()));

		billing.setAmount(ControllerUtils.setValue(billing.getAmount(), billingDTO.getAmount()));

		billing.setBillingDate(ControllerUtils.setValue(billing.getBillingDate(), billingDTO.getBillingDate()));



        billing = billingDao.save(billing);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BillingDTO getBillingDTOById(Integer billingId) {
	
		Billing billing = billingDao.getById(billingId);
			
		
		BillingConvertCriteriaDTO convertCriteria = new BillingConvertCriteriaDTO();
		return(this.convertBillingToBillingDTO(billing,convertCriteria));
	}







}
