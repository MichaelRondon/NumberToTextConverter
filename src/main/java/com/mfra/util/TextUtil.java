package com.mfra.util;

import java.util.List;

public class TextUtil {

	public static String firstLetterInUpperCase(String text) {
		if (text == null || text.length() < 2) {
			throw new IllegalStateException("Invalid input for firstLetterInUpperCase: " + text);
		}
		return String.format("%s%s", text.substring(0, 1).toUpperCase(), text.substring(1));
	}
	
	public static String toString(List<String> texts) {
		return String.join(" ", texts);
	}

}
