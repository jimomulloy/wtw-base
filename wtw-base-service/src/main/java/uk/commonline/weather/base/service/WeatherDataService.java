package uk.commonline.weather.base.service;

import java.util.List;

import uk.commonline.weather.model.Weather;

/**
 * @author Jim O'Mulloy
 *
 *         WTW Base Weather DAO service API
 * 
 */
public interface WeatherDataService {

    /**
     * Get list of recently active Weather data items for given Region id.
     * 
     * @param region
     * @return
     */
    List<Weather> recentForRegion(final long region);

}
