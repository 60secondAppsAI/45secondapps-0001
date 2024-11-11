package com.45secondapps.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<AppDTO> apps;
}





