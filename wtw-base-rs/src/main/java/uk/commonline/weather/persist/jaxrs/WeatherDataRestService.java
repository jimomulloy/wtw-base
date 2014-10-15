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
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.persist.WeatherDAO;

/**
 * @author Jim O'Mulloy
 * 
 *         WTW Base Weather DAO JAXRS Service
 *
 */
@Component
@Path("/weather")
@Transactional
public class WeatherDataRestService extends AbstractCrudService<Weather> {

    @Autowired
    WeatherDAO weatherDAO;

    private Date getDateFromString(String dateString) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(dateString);
            return date;
        } catch (ParseException e) {
            return new Date();
        }
    }

    @Override
    public Class<Weather> getEiClass() {
        return Weather.class;
    }

    @Path("range/region/{region}/fromTime/{fromTime}/hours/{hours}/count/{count}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Weather> getRange(@PathParam("region") long region, @PathParam("fromTime") String fromTime, @PathParam("hours") int hours,
            @PathParam("count") int count) {

        return weatherDAO.getRange(region, getDateFromString(fromTime), hours, count);
    }

    @Override
    protected Dao<Weather> getService() {
        return weatherDAO;
    }

    @Path("recent/region/{region}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Weather> recentForRegion(@PathParam("region") long region) {
        List<Weather> weathers = weatherDAO.recentForRegion(region);
        return weathers;
    }

    @Path("regions/fromTime/{fromTime}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Long> recentRegions(@PathParam("fromTime") String fromTime) {
        List<Long> regions = weatherDAO.recentRegions(getDateFromString(fromTime));
        System.out.println("!!recentRegions regions :" + regions.size());
        return regions;
    }

    public void setWeatherDAO(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }
}
