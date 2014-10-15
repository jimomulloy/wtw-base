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
import uk.commonline.weather.base.service.WeatherForecastDataService;
import uk.commonline.weather.model.WeatherForecast;
import uk.commonline.weather.model.WeatherListMessenger;
import uk.commonline.weather.model.WeatherMessenger;
import uk.commonline.weather.model.WeatherReportMessenger;
import uk.commonline.weather.persist.WeatherForecastDAO;

/**
 * @author Jim O'Mulloy
 * 
 *         JAXRS Client for WTW Base Weather Forecast DAO Service.
 *
 */
public class WeatherForecastDaoClient extends AbstractCrudClient<WeatherForecast> implements WeatherForecastDAO, WeatherForecastDataService {

    private static final String SERVICE_PATH = "wtwbase/webresources/forecast";

    @Override
    protected String getPath() {
        return SERVICE_PATH;
    }

    // Just use ToString() and pass a format e.g.:
    // startDate.ToString("yyyyMMddHHmmss")
    @Override
    public List<WeatherForecast> getRange(final long region, final Date fromTime, final int hours, final int count) {
        GenericType<List<WeatherForecast>> list = new GenericType<List<WeatherForecast>>() {
        };
        WebTarget target = getRestClient().getClient().register(WeatherListMessenger.class).target(getRestClient().createUrl(getPath()))
                .path("range/region/{region}/fromTime/{fromTime}/hours/{hours}/count/{count}");
        target = target.resolveTemplate("region", region).resolveTemplate("fromTime", getStringFromDate(fromTime)).resolveTemplate("hours", hours)
                .resolveTemplate("count", count);
        List<WeatherForecast> entities = target.request(MediaType.APPLICATION_JSON).get(list);
        return entities;
    }

    @Override
    public List<WeatherForecast> getRetro(final long region, final Date fromTime, final Date forecastTime, final int hours, final int count) {
        GenericType<List<WeatherForecast>> list = new GenericType<List<WeatherForecast>>() {
        };
        WebTarget target = getRestClient().getClient().register(WeatherListMessenger.class).target(getRestClient().createUrl(getPath()))
                .path("retro/region/{region}/fromTime/{fromTime}/forecastTime/{forecastTime}/hours/{hours}/count/{count}");
        target = target.resolveTemplate("region", region).resolveTemplate("fromTime", getStringFromDate(fromTime))
                .resolveTemplate("forecastTime", getStringFromDate(forecastTime)).resolveTemplate("hours", hours).resolveTemplate("count", count);
        List<WeatherForecast> entities = target.request(MediaType.APPLICATION_JSON).get(list);
        return entities;
    }

    private String getStringFromDate(Date date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String dateString = df.format(date);
            return dateString;
        } catch (Exception e) {
            // WebApplicationException
            // ...("Date format should be yyyy-MM-dd'T'HH:mm:ss",
            // Status.BAD_REQUEST);
            return "";
        }
    }

    @Override
    public List<WeatherForecast> recentForRegion(long region) {
        GenericType<List<WeatherForecast>> list = new GenericType<List<WeatherForecast>>() {
        };
        WebTarget target = getRestClient().getClient().register(WeatherListMessenger.class).target(getRestClient().createUrl(getPath()))
                .path("recent/region/{region}");
        List<WeatherForecast> entities = target.resolveTemplate("region", region).request(MediaType.APPLICATION_JSON).get(list);
        return entities;
    }

    @Override
    public void setRestClient(RestClient restClient) {
        super.setRestClient(restClient);
        restClient.registerProvider(WeatherListMessenger.class);
        restClient.registerProvider(WeatherReportMessenger.class);
        restClient.registerProvider(WeatherMessenger.class);
        restClient.resetClient();
    }
}
