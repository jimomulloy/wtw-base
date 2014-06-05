package uk.commonline.weather.persist;

import java.util.Iterator;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.commonline.data.access.Dao;
import uk.commonline.weather.model.Location;

/**
 */
public class LocationDataAccessImplTests extends AbstractDataAccessImplTests<Location> {
	@InjectMocks private LocationDAO locationDao;
	@Mock private EntityManager em;
	
	// Test objects
	@Mock private Location location;
	//@Mock private ClosableIterable<Package> pkgs;
	@Mock private Iterator<Location> locationIterator;
	
	/* (non-Javadoc)
	 * @see com.springinpractice.ch11.service.impl.AbstractEntityServiceImplTests#getRepository()
	 */
	@Override
	protected EntityManager getRepository() { return em; }

	/* (non-Javadoc)
	 * @see com.springinpractice.ch11.service.impl.AbstractEntityServiceImplTests#getService()
	 */
	@Override
	protected Dao<Location> getDao() { return locationDao; }
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.locationDao = new JpaLocationDAO();
		
		MockitoAnnotations.initMocks(this);
		
		//when(em.find(locationDao.getClass(), anyLong())).thenReturn(location);
		//when(em.ffindAll()).thenReturn(pkgs);
		//when(pkgs.iterator()).thenReturn(pkgIterator);
	}

}
