package com.45secondapps.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppComponentPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<AppComponentDTO> appComponents;
}




