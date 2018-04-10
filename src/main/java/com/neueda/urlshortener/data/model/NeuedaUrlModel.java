package com.neueda.urlshortener.data.model;

import java.util.Date;
import java.util.List;

public class NeuedaUrlModel {	
	
	public NeuedaUrlModel() {
	}

	public NeuedaUrlModel(String longUrl, String urlTitle, String createUser) {
		super();
		this.longUrl = longUrl;
		this.urlTitle = urlTitle;
		this.createUser = createUser;
	}

	private String shortUrl;
	
	private String longUrl;
	
	private String urlTitle;
	
	private String createUser;	
	
	private Date createDate;	
	
	private List<NeuedaUrlClickModel> clicks;
	
	@Override
	public String toString() {
		return new com.google.gson.Gson().toJson(this);
	}	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

	public List<NeuedaUrlClickModel> getClicks() {
		return clicks;
	}

	public void setClicks(List<NeuedaUrlClickModel> clicks) {
		this.clicks = clicks;
	}

}
