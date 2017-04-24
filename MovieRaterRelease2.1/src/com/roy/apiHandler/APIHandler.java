package com.roy.apiHandler;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

public class APIHandler {
	private String rating;
	private String synopsis;
	private String language;
	private String releaseDate;
	private String jsonResponse;
	private String name;
	private String title;
	Logger log = Logger.getLogger(this.getClass());

	public APIHandler(String name) {
		PropertyConfigurator.configure("log4j.properties");
		this.name = name;
		setDetails();
	}

	private void getDetails() {
		jsonResponse = new RESTClient(this.name).get();
	}

	private void setDetails() {
		getDetails();
		JSONObject obj = new JSONObject(jsonResponse);
		title = obj.getString("Title");
		log.info("Title : " + title);
		releaseDate = obj.getString("Year");
		log.info("Year : " + releaseDate);
		synopsis = obj.getString("Plot");
		log.info("Plot : " + synopsis);
		language = obj.getString("Language");
		log.info("Language : " + language);
		rating = obj.getJSONArray("Ratings").getJSONObject(0).getString("Value");
		log.info("Ratings : " + rating);
	}

	public String getRating() {
		return rating;
	}

	public String getTitle() {
		return title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getLanguage() {
		return language;
	}

}
