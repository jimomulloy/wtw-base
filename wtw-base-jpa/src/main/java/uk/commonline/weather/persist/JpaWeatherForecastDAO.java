package uk.commonline.weather.persist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import uk.commonline.weather.model.Weather;
import uk.commonline.weather.model.WeatherForecast;

public class JpaWeatherForecastDAO extends AbstractJpaDAO<WeatherForecast> implements WeatherForecastDAO {

    public JpaWeatherForecastDAO() {
	setClazz(WeatherForecast.class);
    }

    @SuppressWarnings("unchecked")
    public List<WeatherForecast> recentForRegion(long region) {
	Query query = getEntityManager().createNamedQuery("WeatherForecast.byRegion");
	Long minutesAgo = new Long(60);
	Date date = new Date();
	Date date60MinAgo = new Date(date.getTime() - minutesAgo * 60 * 1000);
	query.setParameter("region", new Long(region));
	query.setParameter("fromTime", date60MinAgo);
	System.out.println("!!Forecast recentForRegion:"+region+", date:"+date60MinAgo);
	ArrayList<WeatherForecast> weather = new ArrayList<WeatherForecast>(query.getResultList());
	return weather;
    }

    @SuppressWarnings("unchecked")
    public List<WeatherForecast> getRange(long region, Date fromTime, int hours, int count){
        Query query;
        ArrayList<WeatherForecast> weather = new ArrayList<WeatherForecast>();
        for (int i = 0; i < count; i++) {
            query = getEntityManager().createNamedQuery("WeatherForecast.range");
            query.setParameter("region", new Long(region));
            query.setParameter("fromTime", fromTime);
            query.setParameter("toTime", new Date());
            weather.addAll(query.getResultList());
            fromTime = new Date(fromTime.getTime() + hours * 60 * 1000);
        }
        return weather;
    }
    
    @SuppressWarnings("unchecked")
    public List<WeatherForecast> getRetro(long region, Date fromTime, Date forecastTime, int hours, int count){
        Query query;
        ArrayList<WeatherForecast> weather = new ArrayList<WeatherForecast>();
        for (int i = 0; i < count; i++) {
            query = getEntityManager().createNamedQuery("WeatherForecast.retro");
            query.setParameter("region", new Long(region));
            query.setParameter("fromTime", fromTime);
            query.setParameter("toTime", new Date());
            query.setParameter("forecastTime", forecastTime);
            weather.addAll(query.getResultList());
            fromTime = new Date(fromTime.getTime() + hours * 60 * 1000);
        }
        return weather;
    }

}
