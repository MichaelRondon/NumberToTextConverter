package com.mfra.service.rest;

import com.mfra.service.NumberToTextConverterService;
import com.mfra.service.rest.model.ConverterResponse;
import java.io.IOException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert")
@Validated
public class ConverterRestController {
	
	private NumberToTextConverterService numberToTextConverterService;
	
	@Autowired
	public ConverterRestController(NumberToTextConverterService numberToTextConverterService){
		this.numberToTextConverterService = numberToTextConverterService;
	}

	@GetMapping(value = "/{input}")
	public ResponseEntity<ConverterResponse> getContent(@Valid 
			@Max(value = Long.MAX_VALUE, message = "the maximum allowed value is 9.223.372.036.854.775.807") 
			@Min(value = Long.MIN_VALUE + 1, message = "the minimun allowed value is -9.223.372.036.854.775.807") 
			@PathVariable
			Long input) throws IOException {
		String convert = numberToTextConverterService.convert(input);
		ConverterResponse converterResponse = new ConverterResponse(input, convert);
		return new ResponseEntity(converterResponse, HttpStatus.OK);
		
	}

}
