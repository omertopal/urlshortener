package com.neueda.urlshortener.data.service;

import java.util.List;

import com.neueda.urlshortener.data.entity.NeuedaUrl;

public interface IUrlService {
	
	public List<NeuedaUrl> findByShortUrl(String shortUrl) throws Exception;
	
	public NeuedaUrl insertUrl(NeuedaUrl url) throws Exception;
	
	public NeuedaUrl saveUrl(NeuedaUrl url) throws Exception;
}
