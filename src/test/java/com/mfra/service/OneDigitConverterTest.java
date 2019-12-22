package com.mfra.service;

import com.mfra.converter.OneDigitConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mfra.converter.ByDigitsConverter;
import com.mfra.util.TextUtil;
import java.util.LinkedList;
import java.util.List;
import org.junit.experimental.categories.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@Category(ConverterUnitTest.class)
public class OneDigitConverterTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public ByDigitsConverter converterStep() {
			return new OneDigitConverter();
		}
	}

	@Autowired
	private ByDigitsConverter converterStep;

	@Test
	public void convert() {
		List<String> texts = new LinkedList<>();
		converterStep.convert(0, texts);
		Assert.assertEquals("zero", TextUtil.toString(texts));
		converterStep.convert(3, texts);
		Assert.assertEquals("zero three", TextUtil.toString(texts));

		texts = new LinkedList<>();
		converterStep.convert(3, texts);
		Assert.assertEquals("three", TextUtil.toString(texts));
		try {
			converterStep.convert(0, texts);
			Assert.fail("You can not append 'zero' to the text");
		} catch (IllegalStateException ex) {
		}
		try {
			converterStep.convert(10, new LinkedList<>());
			Assert.fail("Out of range");
		} catch (IllegalStateException ex) {
		}
		try {
			converterStep.convert(-1, new LinkedList<>());
			Assert.fail("Out of range");
		} catch (IllegalStateException ex) {
		}
	}

}
