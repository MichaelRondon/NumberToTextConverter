package com.mfra.converter;

import java.util.List;

public class OneStepConverterValidator {

	private final long lowerLimit;
	private final long upperLimit;
	private final NumberToTextConverter nextResponsable;

	public OneStepConverterValidator(long lowerLimit, long upperLimit, NumberToTextConverter nextResponsable) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.nextResponsable = nextResponsable;
	}

	protected boolean validate(long input, List<String> texts) {
		if (input < lowerLimit) {
			if (nextResponsable == null) {
				throw new IllegalStateException("Not valid search. Input: " + input);
			}
			nextResponsable.convert(input, texts);
			return false;
		}

		if (input > upperLimit) {
			throw new IllegalStateException("Not valid search. Input: " + input);
		}
		return true;
	}
}
