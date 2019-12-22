package com.mfra.service.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConverterResponse {
	
	private long input;
	private String output;
}
