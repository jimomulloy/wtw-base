package uk.commonline.weather.persist.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;

public class WtwBaseApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public WtwBaseApplication() {
		register(LocationController.class);
		register(WeatherController.class);
	}
}