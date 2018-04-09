package com.neueda.urlshortener.util;

public class UrlParserUtil {
	
	public static final String apiString = "v1"; 
	
	public static boolean isApiCalled(String urlParameter){
		if(urlParameter.startsWith(apiString)){
			return true;
		}
		return false;
	}
}
