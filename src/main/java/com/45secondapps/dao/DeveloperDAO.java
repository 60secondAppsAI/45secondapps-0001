package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.Developer;





public interface DeveloperDAO extends GenericDAO<Developer, Integer> {
  
	List<Developer> findAll();
	






}


