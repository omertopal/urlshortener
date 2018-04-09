package com.neueda.urlshortener.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.repo.UrlMongoRepo;

@Service
public class UrlServiceImpl implements IUrlService {
	
	@Autowired
    private UrlMongoRepo mongoRepository;
		
	@Override
	public List<NeuedaUrl> findByShortUrl(String shortUrl) {
		return mongoRepository.findByShortUrl(shortUrl);
	}

	@Override
	public NeuedaUrl insertUrl(NeuedaUrl url) {		
		return mongoRepository.insert(url);
	}	
	
}
