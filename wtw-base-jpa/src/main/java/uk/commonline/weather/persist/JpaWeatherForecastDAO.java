package uk.commonline.weather.persist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import uk.commonline.weather.model.WeatherForecast;

public class JpaWeatherForecastDAO extends AbstractJpaDAO<WeatherForecast> implements WeatherForecastDAO {

    public JpaWeatherForecastDAO() {
	setClazz(WeatherForecast.class);
    }

    @SuppressWarnings("unchecked")
    public List<WeatherForecast> recentForRegion(final long region) {
	Query query = getEntityManager().createNamedQuery("Weather.byRegion");
	Long minutesAgo = new Long(60);
	Date date = new Date();
	Date date60MinAgo = new Date(date.getTime() - minutesAgo * 60 * 1000);
	query.setParameter("region", new Long(region));
	query.setParameter("date", date60MinAgo);
	ArrayList<WeatherForecast> weather = new ArrayList<WeatherForecast>(query.getResultList());
	return weather;
    }

}
