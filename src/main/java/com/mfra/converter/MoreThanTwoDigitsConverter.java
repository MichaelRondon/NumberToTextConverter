package com.mfra.converter;

import com.mfra.converter.validator.OneStepConverterValidator;
import java.util.List;

public class MoreThanTwoDigitsConverter implements NumberToTextConverter {

	@Override
	public void convert(long input, List<String> texts) {
		MoreThanTwoDigitsConverterParams converterParams = getConverterParams(input);
		if (validate(input, texts, converterParams)) {
			convertOnStep(input, texts, converterParams);
		}
	}

	private boolean validate(long input, List<String> texts, MoreThanTwoDigitsConverterParams converterParams) {
		OneStepConverterValidator oneStepConverterValidator = new OneStepConverterValidator(converterParams.getLowerLimit(),
				converterParams.upperLimit, converterParams.nextDigitsConverter);
		return oneStepConverterValidator.validate(input, texts);
	}

	private void convertOnStep(long input, List<String> texts,
			MoreThanTwoDigitsConverterParams converterParams) {
		long firstDigit = input / converterParams.lowerLimit;
		long nextDigits = input % converterParams.lowerLimit;

		converterParams.getFirstDigitsConverter().convert(firstDigit, texts);
		texts.add(converterParams.name().toLowerCase());
		if (nextDigits != 0) {
			converterParams.getNextDigitsConverter().convert(nextDigits, texts);
		}
	}

	private MoreThanTwoDigitsConverterParams getConverterParams(long input) {
		if (input >= MoreThanTwoDigitsConverterParams.TRILLION.lowerLimit) {
			return MoreThanTwoDigitsConverterParams.TRILLION;
		}
		if (input >= MoreThanTwoDigitsConverterParams.BILLION.lowerLimit) {
			return MoreThanTwoDigitsConverterParams.BILLION;
		}
		if (input >= MoreThanTwoDigitsConverterParams.MILLION.lowerLimit) {
			return MoreThanTwoDigitsConverterParams.MILLION;
		}
		if (input >= MoreThanTwoDigitsConverterParams.THOUSAND.lowerLimit) {
			return MoreThanTwoDigitsConverterParams.THOUSAND;
		}
		return MoreThanTwoDigitsConverterParams.HUNDRED;
	}

	private enum MoreThanTwoDigitsConverterParams {
		HUNDRED(100, new OneDigitConverter(), new TwoDigitsConverter(), 999),
		THOUSAND(1000, new MoreThanTwoDigitsConverter(), new MoreThanTwoDigitsConverter(), 999999l),
		MILLION(1000000, new MoreThanTwoDigitsConverter(), new MoreThanTwoDigitsConverter(), 999999999999l),
		BILLION(1000000000000l, new MoreThanTwoDigitsConverter(), new MoreThanTwoDigitsConverter(), 999999999999999999l),
		TRILLION(1000000000000000000l, new MoreThanTwoDigitsConverter(), new MoreThanTwoDigitsConverter(), Long.MAX_VALUE);

		private final long lowerLimit;
		private final NumberToTextConverter firstDigitsConverter;
		private final NumberToTextConverter nextDigitsConverter;
		private final long upperLimit;

		private MoreThanTwoDigitsConverterParams(long firstDigitsSelectorNumber,
				NumberToTextConverter firstDigitsConverter,
				NumberToTextConverter nextDigitsConverter,
				long upperLimit) {
			this.lowerLimit = firstDigitsSelectorNumber;
			this.firstDigitsConverter = firstDigitsConverter;
			this.nextDigitsConverter = nextDigitsConverter;
			this.upperLimit = upperLimit;
		}

		public long getLowerLimit() {
			return lowerLimit;
		}

		public NumberToTextConverter getFirstDigitsConverter() {
			return firstDigitsConverter;
		}

		public NumberToTextConverter getNextDigitsConverter() {
			return nextDigitsConverter;
		}

		public long getUpperLimit() {
			return upperLimit;
		}
	}
}
