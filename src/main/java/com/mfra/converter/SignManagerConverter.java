package com.mfra.converter;

import com.mfra.converter.validator.OneStepConverterValidator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SignManagerConverter extends ByDigitsConverter {
	
	
	private final MoreThanTwoDigitsConverter moreThanTwoDigitsConverter = 
			new MoreThanTwoDigitsConverter();
	
	@Override
	public void convertInStep(long input, List<String> texts) {
		if(input < 0){
			texts.add("minus");
			input = Math.abs(input);
		}
		moreThanTwoDigitsConverter.convert(input, texts);
	}

	@Override
	protected OneStepConverterValidator getConverterValidator() {
		return new OneStepConverterValidator(Long.MIN_VALUE, Long.MAX_VALUE, null);
	}
}
