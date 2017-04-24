package com.roy.apiHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class RESTClient {

	private String url = "http://www.omdbapi.com/?t=";
	private String jsonString = "";

	Logger log = Logger.getLogger(this.getClass());

	public RESTClient(String data) {
		PropertyConfigurator.configure("log4j.properties");
		try {
			url = url + URLEncoder.encode(data, "UTF-8") + "&r=json";
			log.info("Received URL : " + url);
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException : " + e.getMessage());
		}
	}

	public String get() {
		try {

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				log.debug(response.getStatusLine().getStatusCode());
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			} else {
				log.debug(response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String brCache = "";

			while ((brCache = br.readLine()) != null) {
				jsonString += brCache;
			}

			httpClient.close();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("JSON Ready : " + jsonString);
		return jsonString;
	}

}