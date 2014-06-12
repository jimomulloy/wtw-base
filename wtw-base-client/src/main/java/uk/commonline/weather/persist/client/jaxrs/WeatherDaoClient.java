package uk.commonline.weather.persist.client.jaxrs;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import uk.commonline.data.client.jaxrs.AbstractCrudClient;
import uk.commonline.weather.base.service.WeatherDataService;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.persist.WeatherDAO;

/**
 */
public class WeatherDaoClient extends AbstractCrudClient<Weather> implements
		WeatherDAO, WeatherDataService {

	protected String getPath() {
		return "weather";
	}

	@Override
	public List<Weather> recentForLocation(Location location) {
		GenericType<List<Weather>> list = new GenericType<List<Weather>>() {
		};
		WebTarget target = getRestClient()
				.getClient()
				.target(getRestClient().createUrl(
						"http://localhost:8080/wtwbase/webresources/")).path(getPath())
						.path("recent");
		System.out.println("!!target ="+target);
		List<Weather> entities = target
				.request()
				.post(Entity.entity(location, MediaType.APPLICATION_JSON), list);
		return entities;
	}
}
