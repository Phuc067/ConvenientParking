package com.parking.utils;

public class StringUtils {
	public static String removeExcessBlank(String input)
	{
		 StringBuilder builder = new StringBuilder();
	        boolean isSpace = false;

	        for (char c : input.toCharArray()) {
	            if (Character.isWhitespace(c)) {
	                if (!isSpace) {
	                    builder.append(' ');
	                    isSpace = true;
	                }
	            } else {
	                builder.append(c);
	                isSpace = false;
	            }
	        }
	        return builder.toString().trim();
	    }
	}

