package com.neueda.urlshortener.data.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.neueda.urlshortener.data.entity.NeuedaUrlClick;


@RepositoryRestResource(collectionResourceRel = "clicks")
public interface UrlClickMongoRepo extends MongoRepository<NeuedaUrlClick, Long> {

	 public List<NeuedaUrlClick> findByShortUrl(@Param("shortUrl") String shortUrl);
	 
	 @SuppressWarnings("unchecked")
	 public NeuedaUrlClick insert(@Param("url") NeuedaUrlClick urlToBeCreated);
	 
}
