package com.45secondapps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.45secondapps.domain.Account;
import com.45secondapps.dto.AccountDTO;
import com.45secondapps.dto.AccountSearchDTO;
import com.45secondapps.dto.AccountPageDTO;
import com.45secondapps.dto.AccountConvertCriteriaDTO;
import com.45secondapps.service.GenericService;
import com.45secondapps.dto.common.RequestDTO;
import com.45secondapps.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AccountService extends GenericService<Account, Integer> {

	List<Account> findAll();

	ResultDTO addAccount(AccountDTO accountDTO, RequestDTO requestDTO);

	ResultDTO updateAccount(AccountDTO accountDTO, RequestDTO requestDTO);

    Page<Account> getAllAccounts(Pageable pageable);

    Page<Account> getAllAccounts(Specification<Account> spec, Pageable pageable);

	ResponseEntity<AccountPageDTO> getAccounts(AccountSearchDTO accountSearchDTO);
	
	List<AccountDTO> convertAccountsToAccountDTOs(List<Account> accounts, AccountConvertCriteriaDTO convertCriteria);

	AccountDTO getAccountDTOById(Integer accountId);







}





