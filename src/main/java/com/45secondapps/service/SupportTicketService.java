package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.SupportTicket;
import com.45secondapps.dto.SupportTicketDTO;
import com.45secondapps.dto.SupportTicketSearchDTO;
import com.45secondapps.dto.SupportTicketPageDTO;
import com.45secondapps.dto.SupportTicketConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SupportTicketService extends GenericService<SupportTicket, Integer> {

	List<SupportTicket> findAll();

	ResultDTO addSupportTicket(SupportTicketDTO supportTicketDTO, RequestDTO requestDTO);

	ResultDTO updateSupportTicket(SupportTicketDTO supportTicketDTO, RequestDTO requestDTO);

    Page<SupportTicket> getAllSupportTickets(Pageable pageable);

    Page<SupportTicket> getAllSupportTickets(Specification<SupportTicket> spec, Pageable pageable);

	ResponseEntity<SupportTicketPageDTO> getSupportTickets(SupportTicketSearchDTO supportTicketSearchDTO);
	
	List<SupportTicketDTO> convertSupportTicketsToSupportTicketDTOs(List<SupportTicket> supportTickets, SupportTicketConvertCriteriaDTO convertCriteria);

	SupportTicketDTO getSupportTicketDTOById(Integer supportTicketId);







}





