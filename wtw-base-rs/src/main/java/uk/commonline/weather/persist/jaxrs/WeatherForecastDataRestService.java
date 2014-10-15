package uk.commonline.weather.persist.jaxrs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
 * @author Jim O'Mulloy
 * 
 *         WTW Base Weather Forecast DAO JAXRS Service
 *
 */
@Component
@Path("/forecast")
@Transactional
public class WeatherForecastDataRestService extends AbstractCrudService<WeatherForecast> {

    @Autowired
    WeatherForecastDAO weatherForecastDAO;

    private Date getDateFromString(String dateString) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(dateString);
            return date;
        } catch (ParseException e) {
            // WebApplicationException
            // ...("Date format should be yyyy-MM-dd'T'HH:mm:ss",
            // Status.BAD_REQUEST);
            return new Date();
        }
    }

    @Override
    public Class<WeatherForecast> getEiClass() {
        return WeatherForecast.class;
    }

    @Path("range/region/{region}/fromTime/{fromTime}/hours/{hours}/count/{count}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherForecast> getRange(@PathParam("region") long region, @PathParam("fromTime") String fromTime, @PathParam("hours") int hours,
            @PathParam("count") int count) {
        return weatherForecastDAO.getRange(region, getDateFromString(fromTime), hours, count);
    }

    @Path("retro/region/{region}/fromTime/{fromTime}/forecastTime/{forecastTime}/hours/{hours}/count/{count}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherForecast> getRetro(@PathParam("region") long region, @PathParam("fromTime") String fromTime,
            @PathParam("forecastTime") String forecastTime, @PathParam("hours") int hours, @PathParam("count") int count) {
        return weatherForecastDAO.getRetro(region, getDateFromString(fromTime), getDateFromString(forecastTime), hours, count);
    }

    @Override
    protected Dao<WeatherForecast> getService() {
        return weatherForecastDAO;
    }

    @Path("recent/region/{region}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherForecast> recentForRegion(@PathParam("region") long region) {
        return weatherForecastDAO.recentForRegion(region);
    }

    public void setWeatherDAO(WeatherForecastDAO weatherForecastDAO) {
        this.weatherForecastDAO = weatherForecastDAO;
    }
}
