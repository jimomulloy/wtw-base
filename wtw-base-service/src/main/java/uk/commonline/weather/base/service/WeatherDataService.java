package uk.commonline.weather.base.service;

import java.util.List;

import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;

public interface WeatherDataService {

    // @Path("recent")
    // @POST
    List<Weather> recentForRegion(final long region);

}
