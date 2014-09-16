package uk.commonline.weather.persist;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import uk.commonline.weather.model.Location;
//import org.springframework.stereotype.Repository;

//@Repository
public class JpaLocationDAO extends AbstractJpaDAO<Location> implements LocationDAO {

    public JpaLocationDAO() {
        setClazz(Location.class);
    }

    @Override
    public Location findByZip(final String zip) {
        Query query = getEntityManager().createNamedQuery("Location.uniqueByZip").setParameter("zip", zip);
        Location location = null;
        try {
            location = (Location) query.getSingleResult();
        } catch (NoResultException ex) {
            location = new Location();
            location.setType("Unknown");
        }

        return location;
    }

}