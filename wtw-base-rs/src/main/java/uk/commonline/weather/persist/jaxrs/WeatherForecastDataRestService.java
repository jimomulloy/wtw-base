package uk.commonline.weather.persist.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import uk.commonline.data.access.Dao;
import uk.commonline.data.service.jaxrs.AbstractCrudService;
import uk.commonline.weather.model.WeatherForecast;
import uk.commonline.weather.persist.WeatherForecastDAO;

/**
 * Weather controller.
 * 
 */
@Component
@Path("/forecast")
@Transactional
public class WeatherForecastDataRestService extends AbstractCrudService<WeatherForecast> {

    @Autowired
    WeatherForecastDAO weatherForecastDAO;

    @Override
    protected Dao<WeatherForecast> getService() {
	return weatherForecastDAO;
    }

    public void setWeatherDAO(WeatherForecastDAO weatherForecastDAO) {
	this.weatherForecastDAO = weatherForecastDAO;
    }

    public Class<WeatherForecast> getEiClass() {
	return WeatherForecast.class;
    }

    @Path("recent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherForecast> recentForRegion(@PathParam("region") long region) {
	return weatherForecastDAO.recentForRegion(region);
    }

}
