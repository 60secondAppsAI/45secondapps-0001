package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Log;





public interface LogDAO extends GenericDAO<Log, Integer> {
  
	List<Log> findAll();
	






}


