package org.whatever.weather.persist.client;

import static org.springframework.util.Assert.notNull;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import uk.commonline.weather.model.Weather;

/**
 */
public class WeatherClient {
	private static final Logger log = LoggerFactory.getLogger(WeatherClient.class);
	
	private RestTemplate template;
	private String wtwUrl;
	
	/**
	 * Note: This constructor appends a "/" to the URL if the URL doesn't already end with one.
	 * 
	 * @param template REST template
	 * @param wtwUrl 
	 * @param skybaseUrl Skybase URL
	 */
	public WeatherClient(RestTemplate template, String wtwUrl) {
		this.template = template;
		if (!wtwUrl.endsWith("/")) { wtwUrl += "/"; }
		this.wtwUrl = wtwUrl;
	}
	
	/**
	 * @return REST template
	 */
	public RestTemplate getRestTemplate() { return template; }
	
	public String getWtwUrl() { return wtwUrl; }
	
	/**
	 * @param pkg package
	 */
	public void createWeather(Weather weather) {
		notNull(weather);
		URI location = template.postForLocation(getWeathersUrl(), weather);
		log.info("Created package: {}", location);
	}
	
	/**
	 * @return all packages, sorted
	 */
	public List<Weather> getWeathers() {
		return template
			.getForObject(getWeathersUrl() + "?format=xml", Weather.WeatherListWrapper.class)
			.getList();
	}
	
	private String getWeathersUrl() { return wtwUrl + "weathers"; }
}
