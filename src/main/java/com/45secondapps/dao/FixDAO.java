package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Fix;





public interface FixDAO extends GenericDAO<Fix, Integer> {
  
	List<Fix> findAll();
	






}


