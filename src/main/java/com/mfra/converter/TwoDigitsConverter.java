package com.mfra.converter;

import com.mfra.converter.validator.OneStepConverterValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoDigitsConverter extends ByDigitsConverter {

	private final Map<Long, String> valuesMap = new HashMap<>();
	private OneDigitConverter oneDigitConverter = new OneDigitConverter();

	public TwoDigitsConverter() {
		valuesMap.put(10l, "ten");
		valuesMap.put(11l, "eleven");
		valuesMap.put(12l, "twelve");
		valuesMap.put(13l, "thirteen");
		valuesMap.put(14l, "fourteen");
		valuesMap.put(15l, "fifteen");
		valuesMap.put(16l, "sixteen");
		valuesMap.put(17l, "seventeen");
		valuesMap.put(18l, "eightteen");
		valuesMap.put(19l, "nineteen");
		valuesMap.put(2l, "twenty");
		valuesMap.put(3l, "thirty");
		valuesMap.put(4l, "fourty");
		valuesMap.put(5l, "fifty");
		valuesMap.put(6l, "sixty");
		valuesMap.put(7l, "seventy");
		valuesMap.put(8l, "eighty");
		valuesMap.put(9l, "ninety");
	}

	@Override
	public void convertInStep(long input, List<String> texts) {
		if (input < 19) {
			texts.add(valuesMap.get(input));
			return;
		}

		long firstDigit = input / 10;
		long secondDigit = input % 10;

		texts.add(valuesMap.get(firstDigit));
		if (secondDigit != 0) {
			oneDigitConverter.convert(secondDigit, texts);
		}
	}

	@Override
	protected OneStepConverterValidator getConverterValidator() {
		return new OneStepConverterValidator(10, 99, oneDigitConverter);
	}
}
