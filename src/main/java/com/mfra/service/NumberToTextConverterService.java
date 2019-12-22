package com.mfra.service;

/**
 * Interface for the general solution.
 *
 * @author michael.felipe.rondon@gmail.com
 */
public interface NumberToTextConverterService {
	
	/**
	 * Converts the input number to text.
	 * @param input Number to convert to text
	 * @return Text that describes the input number
	 */
	String convert(long input);

}
