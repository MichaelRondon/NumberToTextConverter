package com.mfra.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneDigitConverter extends ByDigitsConverter {

	private final Map<Long, String> valuesMap = new HashMap<>();

	public OneDigitConverter() {
		valuesMap.put(0l, "zero");
		valuesMap.put(1l, "one");
		valuesMap.put(2l, "two");
		valuesMap.put(3l, "three");
		valuesMap.put(4l, "four");
		valuesMap.put(5l, "five");
		valuesMap.put(6l, "six");
		valuesMap.put(7l, "seven");
		valuesMap.put(8l, "eight");
		valuesMap.put(9l, "nine");
	}

	@Override
	public void convertInStep(long input, List<String> texts) {
		String text = valuesMap.get(input);
		if (!texts.isEmpty() && input == 0) {
			throw new IllegalStateException("The 'zero' text should not be appened");
		}
		texts.add(text);
	}

	@Override
	protected OneStepConverterValidator getConverterValidator() {
		return new OneStepConverterValidator(0, 9, null);
	}
}
