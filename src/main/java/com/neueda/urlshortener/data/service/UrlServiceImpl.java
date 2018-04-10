package com.neueda.urlshortener.data.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.model.NeuedaUrlModel;
import com.neueda.urlshortener.data.repo.UrlMongoRepo;
import com.neueda.urlshortener.error.NeuedaUrlNotFoundException;
import com.neueda.urlshortener.util.DateUtils;
import com.neueda.urlshortener.util.UrlConstants;
import com.neueda.urlshortener.util.UrlUtils;

@Service
public class UrlServiceImpl implements IUrlService {
	private static final Logger log = Logger.getLogger(UrlServiceImpl.class);
	
	@Autowired
    private UrlMongoRepo mongoRepository;
		
	@Override
	public List<NeuedaUrl> findByShortUrl(String shortUrl) throws Exception {
		try{
			log.debug("UrlServiceImpl.findByShortUrl method initiated with parameters: shortUrl:"+shortUrl);
			return mongoRepository.findByShortUrl(shortUrl);	
		}catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public NeuedaUrl saveUrl(NeuedaUrl url) throws Exception{
		try{
			log.debug("saveUrl method initiated with parameters: NeuedaUrl:" + url.toString());
			return mongoRepository.save(url);
		}catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public NeuedaUrl updateUrl(NeuedaUrlModel urlModel) throws Exception {

		try{
			List<NeuedaUrl> urlsFound = findByShortUrl(urlModel.getShortUrl());
			if(urlsFound.size()==1){
				NeuedaUrl urlTobeEdited = urlsFound.get(0);
				urlTobeEdited.setLongUrl(urlModel.getLongUrl());	
				urlTobeEdited.setUrlTitle(urlModel.getUrlTitle());
				NeuedaUrl urlEdited = saveUrl(urlTobeEdited);
				return urlEdited;
			}else{
				throw new NeuedaUrlNotFoundException(urlModel.getShortUrl());
			}
		}catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public NeuedaUrl insertUrl(NeuedaUrl url) throws Exception{		
		try{
			log.debug("UrlServiceImpl.findByShortUrl method initiated with parameters: NeuedaUrl:" + url.toString());
			return mongoRepository.insert(url);
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	@Override
	public NeuedaUrl createUrl(NeuedaUrlModel urlModel) throws Exception {
		try{
			
			boolean isGeneratedShortUrlUnique = false;
			String generatedShortUrl = UrlConstants.STRING_BLANK;
			
			NeuedaUrl urlToBeCreated =  UrlUtils.mapToUrlEntity(urlModel);
			urlToBeCreated.setCreateDate(DateUtils.getCurrentDate());
			
			while(!isGeneratedShortUrlUnique){
				generatedShortUrl = UrlUtils.generateShortUrl();
				//check if this short url already exists in db
				List<NeuedaUrl> urlsFound = findByShortUrl(generatedShortUrl);
				if(urlsFound.isEmpty())
					isGeneratedShortUrlUnique = true;
			}
			urlToBeCreated.setShortUrl(generatedShortUrl);
			
			return  insertUrl(urlToBeCreated);
			
		}catch (Exception e) {
			throw e;
		}
	}

}
