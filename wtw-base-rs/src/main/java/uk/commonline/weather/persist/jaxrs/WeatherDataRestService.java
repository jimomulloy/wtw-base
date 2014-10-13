package uk.commonline.weather.persist.jaxrs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import uk.commonline.weather.model.Atmosphere;
import uk.commonline.weather.model.Condition;
import uk.commonline.weather.model.Precipitation;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.model.Wind;
import uk.commonline.weather.persist.WeatherDAO;

/**
 * Weather controller.
 * 
 */
@Component
@Path("/weather")
@Transactional
public class WeatherDataRestService extends AbstractCrudService<Weather> /*
                                                                          * implements
                                                                          * WeatherDataService
                                                                          */{

    @Autowired
    WeatherDAO weatherDAO;

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
        System.out.println("!!recentRegions regions :"+regions.size());
        return regions;
    }

    public void setWeatherDAO(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }

    @Path("test")
    @GET
    public List<Weather> test() {
        List<Weather> w = new ArrayList<Weather>();
        Weather weather = new Weather();
        Condition condition = new Condition();
        condition.setText("text");
        condition.setDescription("text");
        condition.setIcon("text");
        condition.setMinTemp(20);
        condition.setMaxTemp(30);
        condition.setCode("text");
        String valuee = "";
        try {
            // valuee =
            // xPath.compile("/rss/channel/item/condition/@date").evaluate(response);
            // Date condDate = new
            // SimpleDateFormat("E, dd MMM yyyy HH:mm a z").parse(valuee);
            // condition.setFromTime(condDate);
            // condition.setToTime(condDate);
            condition.setFromTime(new Date());
            condition.setToTime(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            condition.setFromTime(new Date());
            condition.setToTime(new Date());
        }
        // condition.setWeather(weather);
        weather.setCondition(condition);

        Atmosphere atmosphere = new Atmosphere();
        atmosphere.setHumidity(10);
        atmosphere.setVisibility(11);
        atmosphere.setPressure(12);
        atmosphere.setRising("");
        // atmosphere.setWeather(weather);
        weather.setAtmosphere(atmosphere);

        Wind wind = new Wind();
        wind.setChill(10);
        wind.setDirection("text");
        wind.setSpeed(30);
        // wind.setWeather(weather);
        weather.setWind(wind);

        Precipitation precipitation = new Precipitation();
        // precipitation.setWeather(weather);
        weather.setPrecipitation(precipitation);

        weather.setWriteTime(new Date());
        weather.setSource("text");
        w.add(weather);
        weather = weatherDAO.create(weather);
        // return "Hi";
        w.add(weather);
        return w;
    }

}
