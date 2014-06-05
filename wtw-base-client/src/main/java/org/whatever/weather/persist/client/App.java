package org.whatever.weather.persist.client;

import org.springframework.web.client.RestTemplate;

import uk.commonline.weather.model.Location;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! Create Rosendale!" );
        LocationClient client = new LocationClient(new RestTemplate(), "http://localhost:8080/wtw/");
        Location location = new Location();
        location.setCity("London");
        location.setCountry("UK");
        location.setRegion("Rosendale");
        location.setZip("SE21 8LW");
		client.createLocation(location);
        
    }
}
