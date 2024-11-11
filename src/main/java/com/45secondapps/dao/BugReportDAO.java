package com.45secondapps.dao;

import java.util.List;

import com.45secondapps.dao.GenericDAO;
import com.45secondapps.domain.BugReport;





public interface BugReportDAO extends GenericDAO<BugReport, Integer> {
  
	List<BugReport> findAll();
	






}


