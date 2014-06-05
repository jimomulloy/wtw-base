package uk.commonline.weather.persist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;

public class JpaWeatherDAO extends AbstractJpaDAO<Weather> implements WeatherDAO {

	public JpaWeatherDAO() {
		setClazz(Weather.class);
	}

	@SuppressWarnings("unchecked")
	public List<Weather> recentForLocation(final Location location) {
		Query query = getEntityManager().createNamedQuery("Weather.byLocation");
		query.setParameter("location", location);
		ArrayList<Weather> weather = new ArrayList<Weather>(query.getResultList());
		return weather;
	}

}
