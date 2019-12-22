package com.mfra.service;

import com.mfra.converter.MoreThanTwoDigitsConverter;
import com.mfra.converter.NumberToTextConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Category(ConverterUnitTest.class)
public class NumberToTextConverterTest {

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public NumberToTextConverterService numberToTextConverterService() {
            return new DefaultConverterService();
        }
	
        @Bean
        public NumberToTextConverter numberToTextConverter() {
            return new MoreThanTwoDigitsConverter();
        }
    }

    @Autowired
    private NumberToTextConverterService numberToTextConverter;

    @Test
    public void happyPath() {
        System.out.println("numberToTextConverter: " + numberToTextConverter);
        Assert.assertEquals("Zero", numberToTextConverter.convert(0));
        Assert.assertEquals("Thirteen", numberToTextConverter.convert(13));
        Assert.assertEquals("Eighty five", numberToTextConverter.convert(85));
        Assert.assertEquals("Five thousand two hundred thirty seven", numberToTextConverter.convert(5237));
    }

}
