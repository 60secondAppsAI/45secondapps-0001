package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Billing;





public interface BillingDAO extends GenericDAO<Billing, Integer> {
  
	List<Billing> findAll();
	






}


