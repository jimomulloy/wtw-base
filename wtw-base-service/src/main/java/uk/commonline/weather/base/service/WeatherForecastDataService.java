package uk.commonline.weather.base.service;

import java.util.List;

import uk.commonline.weather.model.WeatherForecast;

/**
 * @author Jim O'Mulloy
 *
 *         WTW Base Weather Forecast DAO service API
 * 
 */
public interface WeatherForecastDataService {

    /**
     * Get list of recently active Weather Forecast data items for given Region id.
     * 
     * @param region
     * @return
     */
    List<WeatherForecast> recentForRegion(final long region);

}
