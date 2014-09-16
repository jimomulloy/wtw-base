package uk.commonline.weather.persist.client;

import org.springframework.web.client.RestTemplate;

import uk.commonline.weather.model.Location;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        LocationClient client = new LocationClient(new RestTemplate(), "http://localhost:8080/wtw/");
        Location location = new Location();
        location.setName("London");
        location.setType("city");
        location.setCountry("UK");
        location.setRegion("Rosendale");
        location.setPostal("SE21 8LW");
        client.createLocation(location);

    }
}
