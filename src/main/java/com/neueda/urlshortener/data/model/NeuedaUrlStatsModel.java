package com.neueda.urlshortener.data.model;

public class NeuedaUrlStatsModel {
	
	private String shortUrl;
	
	private String longUrl;
	
	private Integer timesClickedToday;
	
	private Integer timesClickedLastWeek;

	public String getShortUrl() {
		return shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public Integer getTimesClickedToday() {
		return timesClickedToday;
	}

	public Integer getTimesClickedLastWeek() {
		return timesClickedLastWeek;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public void setTimesClickedToday(Integer timesClickedToday) {
		this.timesClickedToday = timesClickedToday;
	}

	public void setTimesClickedLastWeek(Integer timesClickedLastWeek) {
		this.timesClickedLastWeek = timesClickedLastWeek;
	}
	
}
