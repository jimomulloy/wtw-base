package uk.commonline.weather.persist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import uk.commonline.weather.model.Weather;

public class JpaWeatherDAO extends AbstractJpaDAO<Weather> implements WeatherDAO {

    public JpaWeatherDAO() {
	setClazz(Weather.class);
    }

    @SuppressWarnings("unchecked")
    public List<Weather> recentForRegion(final long region) {
	Query query = getEntityManager().createNamedQuery("Weather.byRegion");
	Long minutesAgo = new Long(60);
	Date date = new Date();
	Date date60MinAgo = new Date(date.getTime() - minutesAgo * 60 * 1000);
	query.setParameter("region", new Long(region));
	query.setParameter("date", date60MinAgo);
	ArrayList<Weather> weather = new ArrayList<Weather>(query.getResultList());
	return weather;
    }

}
