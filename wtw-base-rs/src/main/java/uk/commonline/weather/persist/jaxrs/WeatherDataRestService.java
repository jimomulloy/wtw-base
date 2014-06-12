package uk.commonline.weather.persist.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import uk.commonline.data.access.Dao;
import uk.commonline.data.service.jaxrs.AbstractCrudService;
import uk.commonline.weather.base.service.WeatherDataService;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.persist.WeatherDAO;

/**
 * Weather controller.
 * 
 */
@Component
@Path("/weather")
@Transactional
public class WeatherDataRestService extends AbstractCrudService<Weather> /*implements WeatherDataService*/ {

	@Autowired
	WeatherDAO weatherDAO;
	
	@Override
	protected Dao<Weather> getService() {
		return weatherDAO;
	}
	
	public void setWeatherDAO(WeatherDAO weatherDAO) {
		this.weatherDAO = weatherDAO;
	}

	public Class<Weather> getEiClass() {
		return Weather.class;
	}
	
	@Path("recent")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Weather> recentForLocation(final Location location) {
		return weatherDAO.recentForLocation(location);
	}

}
