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

    @Override
    @SuppressWarnings("unchecked")
    public List<Weather> getRange(long region, Date fromTime, int hours, int count) {
        Query query;
        ArrayList<Weather> weather = new ArrayList<Weather>();
        for (int i = 0; i < count; i++) {
            query = getEntityManager().createNamedQuery("Weather.range");
            query.setParameter("region", new Long(region));
            query.setParameter("fromTime", fromTime);
            query.setParameter("toTime", new Date(fromTime.getTime() + 60 * 60 * 1000));
            List<Weather> ws = query.getResultList();
            weather.addAll(ws);
            fromTime = new Date(fromTime.getTime() + hours * 60 * 60 * 1000);
        }
        return weather;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Weather> recentForRegion(long region) {
        Query query = getEntityManager().createNamedQuery("Weather.byRegion");
        Date date = new Date();
        Date date60MinAgo = new Date(date.getTime() - 60 * 60 * 1000);
        query.setParameter("region", new Long(region));
        query.setParameter("fromTime", date60MinAgo);
        ArrayList<Weather> weather = new ArrayList<Weather>(query.getResultList());
        return weather;
    }

}
