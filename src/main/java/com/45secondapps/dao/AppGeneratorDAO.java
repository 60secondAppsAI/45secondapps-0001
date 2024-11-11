package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.AppGenerator;





public interface AppGeneratorDAO extends GenericDAO<AppGenerator, Integer> {
  
	List<AppGenerator> findAll();
	






}


