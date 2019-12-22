package com.mfra.converter;

import com.mfra.converter.validator.OneStepConverterValidator;
import java.util.List;

/**
 * Abstract class that allows the implementation of different classes that will
 * be responsible for the conversion of numbers to texts in different decimal
 * ranges.
 *
 * @author michael.felipe.rondon@gmail.com
 */
public abstract class ByDigitsConverter implements NumberToTextConverter {

	/**
	 *
	 * @return Object that validates the conversion ranges allowed by the
	 * converter
	 */
	protected abstract OneStepConverterValidator getConverterValidator();

	/**
	 *
	 * @param input Number to convert to text
	 * @param texts List of texts that will be concatenated to build the
	 * result text.
	 * @return False if the responsibility for the conversion is assumed by
	 * another object.
	 */
	protected boolean validate(long input, List<String> texts) {
		return getConverterValidator().validate(input, texts);
	}

	/**
	 * Specific conversion implementation for the range of digits.
	 *
	 * @param input Number to convert to text
	 * @param texts List of texts that will be concatenated to build the
	 * result text.
	 */
	protected abstract void convertInStep(long input, List<String> texts);

	@Override
	public final void convert(long input, List<String> texts) {

		if (this.validate(input, texts)) {
			this.convertInStep(input, texts);
		}
	}

}
