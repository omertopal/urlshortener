package com.neueda.urlshortener.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.entity.NeuedaUrlClick;
import com.neueda.urlshortener.data.model.NeuedaUrlClickModel;
import com.neueda.urlshortener.data.model.NeuedaUrlModel;

public class UrlUtils {
	
	public static String generateShortUrl (){
	    boolean useLetters = true;
	    boolean useNumbers = true;	    
		return RandomStringUtils.random(UrlConstants.SHORT_URL_LENGTH, useLetters, useNumbers);
	}
	
	public static boolean isShortUrlValid(String shortUrl){
		if (shortUrl == null || shortUrl.length()!=UrlConstants.SHORT_URL_LENGTH)
			return false;
		else
			return true;
	}
	
	public static NeuedaUrlModel mapToUrlModel (NeuedaUrl url){
		NeuedaUrlModel urlModel = new NeuedaUrlModel();
		urlModel.setShortUrl(url.getShortUrl());
		urlModel.setLongUrl(url.getLongUrl());
		urlModel.setUrlTitle(url.getUrlTitle());
		urlModel.setCreateUser(url.getCreateUser());
		urlModel.setCreateDate(url.getCreateDate());
		List<NeuedaUrlClickModel> clicks = new ArrayList<>();
		urlModel.setClicks(clicks);
		return urlModel;
	}
	
	public static NeuedaUrl mapToUrlEntity (NeuedaUrlModel urlModel){
		NeuedaUrl urlEntity = new NeuedaUrl();
		urlEntity.setShortUrl(urlModel.getShortUrl());
		urlEntity.setLongUrl(urlModel.getLongUrl());
		urlEntity.setUrlTitle(urlModel.getUrlTitle());
		urlEntity.setCreateUser(urlModel.getCreateUser());
		urlEntity.setCreateDate(urlModel.getCreateDate());		
		return urlEntity;
	}
	
	public static NeuedaUrlClickModel mapToUrlClickModel (NeuedaUrlClick url){
		NeuedaUrlClickModel urlClickModel = new NeuedaUrlClickModel();
		urlClickModel.setShortUrl(url.getShortUrl());
		urlClickModel.setIpAddress(url.getIpAddress());
		urlClickModel.setIpLocation(url.getIpLocation());
		urlClickModel.setClickDate(url.getClickDate());
		return urlClickModel;
		
	}
}
