package com.mfra.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mfra.converter.ByDigitsConverter;
import com.mfra.converter.TwoDigitsConverter;
import com.mfra.util.TextUtil;
import java.util.LinkedList;
import java.util.List;
import org.junit.experimental.categories.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@Category(ConverterUnitTest.class)
public class TwoDigitsConverterTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public ByDigitsConverter converterStep() {
			return new TwoDigitsConverter();
		}
	}

	@Autowired
	private ByDigitsConverter converterStep;

	@Test
	public void convert() {
		List<String> texts = new LinkedList<>();
		converterStep.convert(10, texts);
		Assert.assertEquals("ten", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		converterStep.convert(11, texts);
		Assert.assertEquals("eleven", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		converterStep.convert(12, texts);
		Assert.assertEquals("twelve", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		converterStep.convert(20, texts);
		Assert.assertEquals("twenty", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		converterStep.convert(21, texts);
		Assert.assertEquals("twenty one", TextUtil.toString(texts));
		
		texts = new LinkedList<>();
		converterStep.convert(9, texts);
		Assert.assertEquals("nine", TextUtil.toString(texts));
		
		
		try {
			converterStep.convert(100, texts);
			Assert.fail();
		} catch (IllegalStateException ex) {
		}
		try {
			converterStep.convert(-10, texts);
			Assert.fail("Out of range");
		} catch (IllegalStateException ex) {
		}
	}

}
