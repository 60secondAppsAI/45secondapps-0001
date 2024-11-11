package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Component;





public interface ComponentDAO extends GenericDAO<Component, Integer> {
  
	List<Component> findAll();
	






}


