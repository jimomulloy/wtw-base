package uk.commonline.weather.persist.client.jaxrs;

import javax.ws.rs.core.MediaType;

import uk.commonline.data.client.jaxrs.AbstractCrudClient;
import uk.commonline.weather.base.service.LocationDataService;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.persist.LocationDAO;

/**
 * @author Jim O'Mulloy
 * 
 *         JAXRS Client for WTW Base Location DAO Service.
 *
 */
public class LocationDaoClient extends AbstractCrudClient<Location> implements LocationDAO, LocationDataService {

    private static final String SERVICE_PATH = "wtwbase/webresources/location";

    @Override
    protected String getPath() {
        return SERVICE_PATH;
    }
    
    @Override
    public Location findByZip(final String zip) {
        Location location = getRestClient().getClient().target(getRestClient().createUrl(getPath())).path("zip/{zip}").resolveTemplate("zip", zip).request(MediaType.APPLICATION_JSON).get(Location.class);
        if (location == null) {
            location = newCiInstance();
        }
        return location;
    }

}
