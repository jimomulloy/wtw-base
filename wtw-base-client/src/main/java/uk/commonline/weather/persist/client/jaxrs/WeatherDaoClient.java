package uk.commonline.weather.persist.client.jaxrs;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import uk.commonline.data.client.jaxrs.AbstractCrudClient;
import uk.commonline.data.client.jaxrs.RestClient;
import uk.commonline.weather.base.service.WeatherDataService;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.model.WeatherListMessenger;
import uk.commonline.weather.model.WeatherMessenger;
import uk.commonline.weather.model.WeatherReportMessenger;
import uk.commonline.weather.persist.WeatherDAO;

/**
 */
public class WeatherDaoClient extends AbstractCrudClient<Weather> implements WeatherDAO, WeatherDataService {

    public void setRestClient(RestClient restClient) {
	super.setRestClient(restClient);
	restClient.registerProvider(WeatherListMessenger.class);
	restClient.registerProvider(WeatherReportMessenger.class);
	restClient.registerProvider(WeatherMessenger.class);
	restClient.resetClient();
    }

    protected String getPath() {
	return "weather";
    }

    @Override
    public List<Weather> recentForRegion(long region) {
	GenericType<List<Weather>> list = new GenericType<List<Weather>>() {
	};
	WebTarget target = getRestClient().getClient().register(WeatherListMessenger.class)
		.target(getRestClient().createUrl("http://localhost:8080/wtwbase/webresources/")).path(getPath()).path("recent");
	List<Weather> entities = target.resolveTemplate("region", region).request(MediaType.APPLICATION_JSON).get(list);
	return entities;
    }
}
