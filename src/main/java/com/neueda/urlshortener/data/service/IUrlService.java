package com.neueda.urlshortener.data.service;

import java.util.List;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.model.NeuedaUrlModel;
import com.neueda.urlshortener.data.model.NeuedaUrlStatsModel;

public interface IUrlService {
	
	public List<NeuedaUrl> findByShortUrl(String shortUrl) throws Exception;
	
	public NeuedaUrlModel getUrlByShortUrl(String shortUrl) throws Exception;
	
	public NeuedaUrl insertUrl(NeuedaUrl url) throws Exception;
	
	public NeuedaUrl createUrl(NeuedaUrlModel url) throws Exception;
	
	public NeuedaUrl updateUrl(NeuedaUrlModel url) throws Exception;
	
	public NeuedaUrl saveUrl(NeuedaUrl url) throws Exception;
	
	public NeuedaUrlStatsModel getUrlStats(String shortUrl) throws Exception;
}
