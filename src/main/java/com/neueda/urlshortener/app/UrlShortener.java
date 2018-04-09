

package com.neueda.urlshortener.app;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages={"com.neueda"})
@EnableMongoRepositories ("com.neueda.urlshortener.data.repo")
public class UrlShortener {

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		SpringApplication.run(UrlShortener.class, args);
	}	
}
