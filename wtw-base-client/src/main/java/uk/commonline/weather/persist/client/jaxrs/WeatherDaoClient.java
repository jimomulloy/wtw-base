package uk.commonline.weather.persist.client.jaxrs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import uk.commonline.data.client.jaxrs.AbstractCrudClient;
import uk.commonline.data.client.jaxrs.RestClient;
import uk.commonline.weather.base.service.WeatherDataService;
import uk.commonline.weather.model.LongListMessenger;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.model.WeatherListMessenger;
import uk.commonline.weather.model.WeatherMessenger;
import uk.commonline.weather.model.WeatherReportMessenger;
import uk.commonline.weather.persist.WeatherDAO;

/**
 * @author Jim O'Mulloy
 * 
 *         JAXRS Client for WTW Base Weather DAO Service.
 *
 */
public class WeatherDaoClient extends AbstractCrudClient<Weather> implements WeatherDAO, WeatherDataService {

    private static final String SERVICE_PATH = "wtwbase/webresources/weather";

    @Override
    protected String getPath() {
        return SERVICE_PATH;
    }

    @Override
    public List<Weather> getRange(final long region, final Date fromTime, final int hours, final int count) {
        GenericType<List<Weather>> list = new GenericType<List<Weather>>() {
        };
        WebTarget target = getRestClient().getClient().register(WeatherListMessenger.class).target(getRestClient().createUrl(getPath()))
                .path("range/region/{region}/fromTime/{fromTime}/hours/{hours}/count/{count}");
        target = target.resolveTemplate("region", region).resolveTemplate("fromTime", getStringFromDate(fromTime)).resolveTemplate("hours", hours)
                .resolveTemplate("count", count);
        List<Weather> entities = target.request(MediaType.APPLICATION_JSON).get(list);
        return entities;
    }

    private String getStringFromDate(Date date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String dateString = df.format(date);
            return dateString;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public List<Weather> recentForRegion(long region) {
        GenericType<List<Weather>> list = new GenericType<List<Weather>>() {
        };
        WebTarget target = getRestClient().getClient().register(WeatherListMessenger.class).target(getRestClient().createUrl(getPath()))
                .path("recent/region/{region}");
        List<Weather> entities = target.resolveTemplate("region", region).request(MediaType.APPLICATION_JSON).get(list);
        return entities;
    }

    @Override
    public List<Long> recentRegions(Date fromTime) {
        GenericType<List<Long>> list = new GenericType<List<Long>>() {
        };
        WebTarget target = getRestClient().getClient().register(LongListMessenger.class).target(getRestClient().createUrl(getPath()))
                .path("regions/fromTime/{fromTime}");
        List<Long> entities = target.resolveTemplate("fromTime", getStringFromDate(fromTime)).request(MediaType.APPLICATION_JSON).get(list);
        return entities;
    }

    @Override
    public void setRestClient(RestClient restClient) {
        super.setRestClient(restClient);
        restClient.registerProvider(WeatherListMessenger.class);
        restClient.registerProvider(WeatherReportMessenger.class);
        restClient.registerProvider(WeatherMessenger.class);
        restClient.registerProvider(LongListMessenger.class);
        restClient.resetClient();
    }
}
