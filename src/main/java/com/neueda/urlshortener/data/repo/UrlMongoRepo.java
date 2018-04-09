package com.neueda.urlshortener.data.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.neueda.urlshortener.data.entity.NeuedaUrl;


@RepositoryRestResource(collectionResourceRel = "urls")
public interface UrlMongoRepo extends MongoRepository<NeuedaUrl, Long> {

	 public List<NeuedaUrl> findByShortUrl(@Param("shortUrl") String shortUrl);
	 
	 public NeuedaUrl insert(@Param("url") NeuedaUrl url);
}
