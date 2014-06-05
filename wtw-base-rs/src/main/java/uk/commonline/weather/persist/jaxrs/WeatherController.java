package uk.commonline.weather.persist.jaxrs;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.commonline.data.access.Dao;
import uk.commonline.data.service.jaxrs.AbstractCrudController;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.persist.WeatherDAO;

/**
 * Weather controller.
 * 
 */
@Component
@Path("/weather")
public class WeatherController extends AbstractCrudController<Weather> {

	@Autowired
	WeatherDAO weatherDAO;
	
	@Override
	protected Dao<Weather> getService() {
		return weatherDAO;
	}
	
	public void setWeatherDAO(WeatherDAO weatherDAO) {
		this.weatherDAO = weatherDAO;
	}
}
