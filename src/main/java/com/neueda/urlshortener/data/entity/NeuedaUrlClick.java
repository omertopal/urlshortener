package com.neueda.urlshortener.data.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "clicks")
public class NeuedaUrlClick {
	
	private String shortUrl;
	
	private String ipLocation;
	
	private String ipAddress;
	
	private Date clickDate;
	
	@Override
	public String toString() {
		return new com.google.gson.Gson().toJson(this);
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public String getIpLocation() {
		return ipLocation;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public Date getClickDate() {
		return clickDate;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public void setIpLocation(String ipLocation) {
		this.ipLocation = ipLocation;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setClickDate(Date clickDate) {
		this.clickDate = clickDate;
	}
}
