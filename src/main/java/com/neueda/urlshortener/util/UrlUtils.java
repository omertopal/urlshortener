package com.neueda.urlshortener.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class UrlUtils {

	public static String shortenUrl(String longUrl){		
		return Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
	}
}
