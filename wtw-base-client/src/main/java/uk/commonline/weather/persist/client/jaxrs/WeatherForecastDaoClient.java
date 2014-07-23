package uk.commonline.weather.persist.client.jaxrs;

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
 */
public class WeatherForecastDaoClient extends AbstractCrudClient<WeatherForecast> implements WeatherForecastDAO, WeatherForecastDataService {

    public void setRestClient(RestClient restClient) {
	super.setRestClient(restClient);
	restClient.registerProvider(WeatherListMessenger.class);
	restClient.registerProvider(WeatherReportMessenger.class);
	restClient.registerProvider(WeatherMessenger.class);
	restClient.resetClient();
    }

    protected String getPath() {
	return "forecast";
    }

    @Override
    public List<WeatherForecast> recentForRegion(long region) {
	GenericType<List<WeatherForecast>> list = new GenericType<List<WeatherForecast>>() {
	};
	WebTarget target = getRestClient().getClient().register(WeatherListMessenger.class)
		.target(getRestClient().createUrl("http://localhost:8080/wtwbase/webresources/")).path(getPath()).path("recent");
	List<WeatherForecast> entities = target.resolveTemplate("region", region).request(MediaType.APPLICATION_JSON).get(list);
	return entities;
    }
}
