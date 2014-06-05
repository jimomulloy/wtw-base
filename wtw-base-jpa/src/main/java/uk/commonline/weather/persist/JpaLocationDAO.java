package uk.commonline.weather.persist;

import javax.persistence.Query;

import uk.commonline.weather.model.Location;
//import org.springframework.stereotype.Repository;

//@Repository
public class JpaLocationDAO extends AbstractJpaDAO<Location> implements LocationDAO {
	
	public JpaLocationDAO() {
		System.out.println("!!HI GUYS!!");
		setClazz(Location.class);
	}

	public Location findByZip(final String zip) {
		Query query = getEntityManager().createNamedQuery("Location.uniqueByZip").setParameter("zip", zip);
		Location location = (Location) query.getSingleResult();
		return location;
	}

}