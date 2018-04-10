package com.neueda.urlshortener.data.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neueda.urlshortener.data.entity.NeuedaUrlClick;
import com.neueda.urlshortener.data.repo.UrlClickMongoRepo;

@Service
public class UrlClickServiceImpl implements IUrlClickService {
	private static final Logger log = Logger.getLogger(UrlClickServiceImpl.class);
	
	@Autowired
    private UrlClickMongoRepo mongoRepository;
		
	@Override
	public List<NeuedaUrlClick> findByShortUrl(String shortUrl) throws Exception {
		try{
			log.debug("UrlClickServiceImpl.findByShortUrl method initiated with parameters: shortUrl:"+shortUrl);
			return mongoRepository.findByShortUrl(shortUrl);	
		}catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public NeuedaUrlClick insertClick(NeuedaUrlClick urlClick) throws Exception{		
		try{
			log.debug("UrlClickServiceImpl.findByShortUrl method initiated with parameters: NeuedaUrl:" + urlClick.toString());
			return mongoRepository.insert(urlClick);
		}catch (Exception e) {
			throw e;
		}
		
	}
	
}
