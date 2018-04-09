package com.neueda.urlshortener.data.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.repo.UrlMongoRepo;

@Service
public class UrlServiceImpl implements IUrlService {
	private static final Logger log = Logger.getLogger(UrlServiceImpl.class);
	
	@Autowired
    private UrlMongoRepo mongoRepository;
		
	@Override
	public List<NeuedaUrl> findByShortUrl(String shortUrl) throws Exception {
		try{
			log.debug("findByShortUrl method initiated with parameters: shortUrl"+shortUrl);
			return mongoRepository.findByShortUrl(shortUrl);	
		}catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public NeuedaUrl insertUrl(NeuedaUrl url) throws Exception{		
		try{
			log.debug("findByShortUrl method initiated with parameters: shortUrl"+url.toString());
			return mongoRepository.insert(url);
		}catch (Exception e) {
			throw e;
		}
		
	}	
	
}
