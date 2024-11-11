package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Billing;
import com.45secondapps.dto.BillingDTO;
import com.45secondapps.dto.BillingSearchDTO;
import com.45secondapps.dto.BillingPageDTO;
import com.45secondapps.dto.BillingConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BillingService extends GenericService<Billing, Integer> {

	List<Billing> findAll();

	ResultDTO addBilling(BillingDTO billingDTO, RequestDTO requestDTO);

	ResultDTO updateBilling(BillingDTO billingDTO, RequestDTO requestDTO);

    Page<Billing> getAllBillings(Pageable pageable);

    Page<Billing> getAllBillings(Specification<Billing> spec, Pageable pageable);

	ResponseEntity<BillingPageDTO> getBillings(BillingSearchDTO billingSearchDTO);
	
	List<BillingDTO> convertBillingsToBillingDTOs(List<Billing> billings, BillingConvertCriteriaDTO convertCriteria);

	BillingDTO getBillingDTOById(Integer billingId);







}





