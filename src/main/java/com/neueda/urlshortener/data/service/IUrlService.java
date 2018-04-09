package com.neueda.urlshortener.data.service;

import java.util.List;

import com.neueda.urlshortener.data.entity.NeuedaUrl;

public interface IUrlService {
	
	public List<NeuedaUrl> findByShortUrl(String shortUrl);
	
	public NeuedaUrl insertUrl(NeuedaUrl url);
}
