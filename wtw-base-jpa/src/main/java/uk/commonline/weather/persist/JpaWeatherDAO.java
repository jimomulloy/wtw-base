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
    public List<Weather> recentForRegion(long region) {
        System.out.println("!!Weather recentForRegion REGION:" + region);
        Query query = getEntityManager().createNamedQuery("Weather.byRegion");
        Long minutesAgo = new Long(5);
        Date date = new Date();
        Date date60MinAgo = new Date(date.getTime() - minutesAgo * 60 * 1000);
        query.setParameter("region", new Long(region));
        query.setParameter("fromTime", date60MinAgo);
        System.out.println("!!Weather recentForRegion:" + region + ", date:" + date60MinAgo);
        ArrayList<Weather> weather = new ArrayList<Weather>(query.getResultList());
        // Query query2 = this.entityManager.createNamedQuery("select from
        // Weather w
        // where w.region = :region and w.writeTime >= :fromTime and w.forecast
        // = false");
        // List results = query.getResultList();
        return weather;
    }

    @SuppressWarnings("unchecked")
    public List<Weather> getRange(long region, Date fromTime, int hours, int count) {
        Query query;
        ArrayList<Weather> weather = new ArrayList<Weather>();
        for (int i = 0; i < count; i++) {
            query = getEntityManager().createNamedQuery("Weather.range");
            query.setParameter("region", new Long(region));
            query.setParameter("fromTime", fromTime);
            query.setParameter("toTime", new Date());
            weather.addAll(query.getResultList());
            fromTime = new Date(fromTime.getTime() + hours * 60 * 1000);
        }
        // Query query2 = this.entityManager.createNamedQuery("select from
        // Weather w
        // where w.region = :region and w.writeTime >= :fromTime and w.forecast
        // = false
        // and (w.id in(select w1.id from Weather w1 where ))");
        // List results = query.getResultList();
        return weather;
    }

    /*
     * SELECT datepart(hh, MyDate) as MyHour ... FROM ... WHERE MyDate BETWEEN
     * '2005-01-12' and '2005-12-31' and MyHour = 7 SELECT * FROM main WHERE
     * calltime BETWEEN '2011-01-12' and '2011-12-31' AND (datepart(hh,
     * calltime) = 7) select DATEPART(hh, Calltime)as MyHour, DATEPART(dd,
     * Calltime)as MyDay, DATEPART(yy, calltime)as MyYear into Mytemp from
     * deployment order by myyear, MyDay, MyHour select *,COUNT(myhour)as
     * MyCount from mytemp group by myyear,myday,myhour order by MyCount Drop
     * mytemp SELECT * FROM MyTable WHERE [dateColumn] > '3/1/2009' AND
     * [dateColumn] <= DATEADD(day,'3/31/2009',1) --make it inclusive for a
     * datetime type AND DATEPART(hh,[dateColumn]) >= 6 AND
     * DATEPART(hh,[dateColumn]) <= 22 -- gets the hour of the day from the
     * datetime AND DATEPART(dw,[dateColumn]) >= 3 AND DATEPART(dw,[dateColumn])
     * <= 5 -- gets the day of the week from the datetime SELECT COUNT(*) FROM
     * mytable WHERE EXTRACT(HOUR_SECOND from TIME) between 60000 and 120000
     * YEAR({d '2011-12-31'}) is evaluated to 2011. MONTH({d '2011-12-31'}) is
     * evaluated to 12. DAY({d '2011-12-31'}) is evaluated to 31. HOUR({t
     * '23:59:00'}) is evaluated to 23. MINUTE({t '23:59:00'}) is evaluated to
     * 59. SECOND({t '23:59:00'}) is evaluated to 0. You can simply pass a long
     * (from.getTime()) in the comparison, if it is represented as long in the
     * DB.
     * 
     * Otherwise you can use these functiomns: second(...), minute(...),
     * hour(...), day(...), month(...), and year(...) Query q = em.createQuery(
     * "SELECT t FROM Foo t WHERE ( EXTRACT(HOUR FROM t.std) * 60 + EXTRACT(MINUTE FROM t.std) ) >= :p_1"
     * );
     * 
     * Calendar cal = Calendar.getInstance(); cal.setTime(myDate);
     * 
     * int minutes = cal.get(Calendar.HOUR_OF_DAY) * 60 +
     * cal.get(Calendar.MINUTE); q.setParameter("p_1", minutes);
     */
}
