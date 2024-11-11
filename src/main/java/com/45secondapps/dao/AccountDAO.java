package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Account;





public interface AccountDAO extends GenericDAO<Account, Integer> {
  
	List<Account> findAll();
	






}


