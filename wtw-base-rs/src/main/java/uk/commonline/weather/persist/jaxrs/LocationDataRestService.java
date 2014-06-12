package uk.commonline.weather.persist.jaxrs;

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
import uk.commonline.weather.model.Location;
import uk.commonline.weather.persist.LocationDAO;

/**
 * Location controller.
 * 
 */
@Path("/location")
@Component
@Transactional
public class LocationDataRestService extends AbstractCrudService<Location> /* implements LocationDataService */ {

	@Autowired
	LocationDAO locationDAO;
	
	@Override
	protected Dao<Location> getService() {
		return locationDAO;
	}
		
	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public Class<Location> getEiClass() {
		return Location.class;
	}
	
	@GET
	@Path("zip/{zip}")
	@Produces(MediaType.APPLICATION_JSON)
	public Location findByZip(@PathParam("zip") String zip) {
		System.out.println("!!LocationDataRestService findbyzip");
		return locationDAO.findByZip(zip);
	}
}
