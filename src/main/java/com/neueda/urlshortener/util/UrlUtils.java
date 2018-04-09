package com.neueda.urlshortener.util;

import org.apache.commons.lang3.RandomStringUtils;

public class UrlUtils {
	
	public static String generateShortUrl (){
	    boolean useLetters = true;
	    boolean useNumbers = false;	    
		return RandomStringUtils.random(UrlConstants.SHORT_URL_LENGTH, useLetters, useNumbers);
	}
	
	public static boolean isShortUrlValid(String shortUrl){
		if (shortUrl == null || shortUrl.length()!=UrlConstants.SHORT_URL_LENGTH)
			return false;
		else
			return true;
	}

}
