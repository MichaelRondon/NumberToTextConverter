package com.mfra.converter;

import java.util.List;

public abstract class ByDigitsConverter implements NumberToTextConverter{

	protected abstract OneStepConverterValidator getConverterValidator();

	protected boolean validate(long input, List<String> texts) {
		return getConverterValidator().validate(input, texts);
	}

	protected abstract void convertInStep(long input, List<String> texts);

	@Override
	public final void convert(long input,List<String> texts) {

		if (this.validate(input, texts)) {
			this.convertInStep(input, texts);
		}
	}

}
