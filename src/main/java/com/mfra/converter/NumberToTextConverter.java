package com.mfra.converter;

import java.util.List;

/**
 * Interface that allows the implementation of different classes that will
 * be responsible for the conversion of numbers to texts in different decimal
 * ranges.
 *
 * @author michael.felipe.rondon@gmail.com
 */
public interface NumberToTextConverter {
	
	/**
	 * Converts the input number to list of text to be concatened.
	 * @param input Number to convert to text
	 * @param texts List of texts that will be concatenated to build the
	 * result text.
	 */
	void convert(long input, List<String> texts);

}
