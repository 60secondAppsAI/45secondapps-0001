package com.45secondapps.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppGeneratorPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<AppGeneratorDTO> appGenerators;
}





