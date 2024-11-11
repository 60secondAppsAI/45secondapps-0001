package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Request;





public interface RequestDAO extends GenericDAO<Request, Integer> {
  
	List<Request> findAll();
	






}


