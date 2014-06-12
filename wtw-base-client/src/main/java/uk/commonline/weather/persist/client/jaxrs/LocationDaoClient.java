package uk.commonline.weather.persist.client.jaxrs;

import javax.ws.rs.core.MediaType;

import uk.commonline.data.client.jaxrs.AbstractCrudClient;
import uk.commonline.weather.base.service.LocationDataService;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.persist.LocationDAO;

/**
 */
public class LocationDaoClient extends AbstractCrudClient<Location> implements LocationDAO, LocationDataService {

	protected String getPath() {
		return "location";
	}
	
	public Location findByZip(final String zip) {
		System.out.println("!!LocationDaoClient findbyzip");
		Location location = getRestClient()
				.getClient()
				.target(getRestClient().createUrl(
						"http://localhost:8080/wtwbase/webresources/"))
				.path(getPath()).path("zip/{zip}").resolveTemplate("zip", zip)
				.request(MediaType.APPLICATION_JSON).get(Location.class);
		if (location == null) {
			location = newCiInstance();
		}
		return location;
	}
}
