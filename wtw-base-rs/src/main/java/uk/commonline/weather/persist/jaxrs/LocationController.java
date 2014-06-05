package uk.commonline.weather.persist.jaxrs;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.commonline.data.access.Dao;
import uk.commonline.data.service.jaxrs.AbstractCrudController;
import uk.commonline.weather.model.Location;
import uk.commonline.weather.persist.LocationDAO;

/**
 * Location controller.
 * 
 */
@Path("/location")
@Component
public class LocationController extends AbstractCrudController<Location> {

	@Autowired
	LocationDAO locationDAO;
	
	@Override
	protected Dao<Location> getService() {
		return locationDAO;
	}
		
	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

}
