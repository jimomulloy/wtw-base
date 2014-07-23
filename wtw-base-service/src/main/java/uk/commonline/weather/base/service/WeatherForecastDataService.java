package uk.commonline.weather.base.service;

import java.util.List;

import uk.commonline.weather.model.WeatherForecast;

public interface WeatherForecastDataService {

    // @Path("recent")
    // @POST
    List<WeatherForecast> recentForRegion(final long region);

}
