package com.roy.apiHandler;

public class APIHandler {
	private String rating;
	private String synopsis; 
	private String language; 
	private String releaseDate; 
	private String jsonResponse;
	
	public APIHandler(String name){
		setDetails();
	}
	
	private void getDetails(){
		
	}
	
	private void setDetails(){
		getDetails();
	}
	
	public String getRating(){
		return rating;
	}
	
	public String getReleaseDate(){
		return releaseDate;
	}
	public String getSynopsis(){
		return synopsis;
	}
	public String getLanguage(){
		return language;
	}
	
	
}
