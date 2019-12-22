package com.mfra.service;

import com.mfra.converter.NumberToTextConverter;
import com.mfra.util.TextUtil;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultConverterService implements NumberToTextConverterService{
	
	@Autowired
	private NumberToTextConverter numberToTextConverter;

	@Override
	public String convert(long input) {
		List<String> texts = new LinkedList<>();
		numberToTextConverter.convert(input, texts);
		return TextUtil.firstLetterInUpperCase(TextUtil.toString(texts));
	}

}
