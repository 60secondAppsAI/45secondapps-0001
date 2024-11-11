package com.45secondapps.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="bug_reports")
@Getter @Setter @NoArgsConstructor
public class BugReport {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="bug_report_id")
	private Integer bugReportId;
    
  	@Column(name="bug_description")
	private String bugDescription;
    
  	@Column(name="report_date")
	private Date reportDate;
    
	




}
