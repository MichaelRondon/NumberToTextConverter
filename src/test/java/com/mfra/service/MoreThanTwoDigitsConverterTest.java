package com.mfra.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mfra.converter.MoreThanTwoDigitsConverter;
import com.mfra.converter.NumberToTextConverter;
import com.mfra.util.TextUtil;
import java.util.LinkedList;
import java.util.List;
import org.junit.experimental.categories.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@Category(ConverterUnitTest.class)
public class MoreThanTwoDigitsConverterTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public NumberToTextConverter converterStep() {
			return new MoreThanTwoDigitsConverter();
		}
	}

	@Autowired
	private NumberToTextConverter numberToTextConverter;

	@Test
	public void convert() {
		
		List<String> texts = new LinkedList<>();
		numberToTextConverter.convert(1321, texts);
		Assert.assertEquals("one thousand three hundred twenty one", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(100, texts);
		Assert.assertEquals("one hundred", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(101, texts);
		Assert.assertEquals("one hundred one", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(111, texts);
		Assert.assertEquals("one hundred eleven", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(220, texts);
		Assert.assertEquals("two hundred twenty", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(221, texts);
		Assert.assertEquals("two hundred twenty one", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(99, texts);
		Assert.assertEquals("ninety nine", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(1000, texts);
		Assert.assertEquals("one thousand", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(1001, texts);
		Assert.assertEquals("one thousand one", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(1987654321, texts);
		Assert.assertEquals("one thousand nine hundred eighty seven million six hundred fifty four thousand three hundred twenty one", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(10000000000l, texts);
		Assert.assertEquals("ten thousand million", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(100000l, texts);
		Assert.assertEquals("one hundred thousand", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(10000l, texts);
		Assert.assertEquals("ten thousand", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(1000000l, texts);
		Assert.assertEquals("one million", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(10000000l, texts);
		Assert.assertEquals("ten million", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(100000000l, texts);
		Assert.assertEquals("one hundred million", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(1000000000l, texts);
		Assert.assertEquals("one thousand million", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(100000000000l, texts);
		Assert.assertEquals("one hundred thousand million", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(100000000000000000l, texts);
		Assert.assertEquals("one hundred thousand billion", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		numberToTextConverter.convert(Long.MAX_VALUE, texts);
		Assert.assertEquals("nine trillion two hundred twenty three thousand three hundred seventy two billion thirty six thousand eight hundred fifty four million seven hundred seventy five thousand eight hundred seven", TextUtil.toString(texts));
		
		try {
			numberToTextConverter.convert(-1, texts);
			Assert.fail("Out of range");
		} catch (IllegalStateException ex) {
		}
	}

}
