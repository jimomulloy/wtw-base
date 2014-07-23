package uk.commonline.weather.persist.jaxrs;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.commonline.weather.model.Weather;
import uk.commonline.weather.persist.WeatherDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BaseDaoTest extends TestCase {


    @Autowired
    WeatherDAO weatherDAO;

    public void setWeatherDAO(WeatherDAO weatherDAO) {
	this.weatherDAO = weatherDAO;
    }

    @Test
    public void dummy() throws Exception {

    }

    //@Test
    public void test() throws Exception {

	Weather weather = new Weather();
	weather = weatherDAO.update(weather);
	System.out.println("!!Updated weather:" + weather.getId());
    }
}