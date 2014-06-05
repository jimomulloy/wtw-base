package org.whatever.weather.persist.client;

import static org.springframework.util.Assert.notNull;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import uk.commonline.weather.model.Location;

/**
 */
public class LocationClient {
	private static final Logger log = LoggerFactory.getLogger(LocationClient.class);
	
	@Inject
	private RestTemplate template;
	
	private String wtwUrl;
	
	/**
	 * Note: This constructor appends a "/" to the URL if the URL doesn't already end with one.
	 * 
	 * @param template REST template
	 * @param wtwUrl 
	 * @param skybaseUrl Skybase URL
	 */
	public LocationClient(RestTemplate template, String wtwUrl) {
		this.template = template;
		if (!wtwUrl.endsWith("/")) { wtwUrl += "/"; }
		this.wtwUrl = wtwUrl;
	}
	
	/**
	 * @return REST template
	 */
	public RestTemplate getRestTemplate() { return template; }
	
	public void setRestTemplate(RestTemplate template) { this.template = template; }
	
	public String getWtwUrl() { return wtwUrl; }
	
	/**
	 * @param pkg package
	 */
	public void createLocation(Location location) {
		notNull(location);
		URI uri = template.postForLocation(getLocationsUrl(), location);
		log.info("Created package: {}", uri);
	}
	
	/**
	 * @return all packages, sorted
	 */
	public List<Location> getLocations() {
		return template
			.getForObject(getLocationsUrl() + "?format=xml", Location.LocationListWrapper.class)
			.getList();
	}
	
	private String getLocationsUrl() { return wtwUrl + "location"; }
}
