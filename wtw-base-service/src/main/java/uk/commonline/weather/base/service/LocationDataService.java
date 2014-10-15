package uk.commonline.weather.base.service;

import uk.commonline.weather.model.Location;

/**
 * @author Jim O'Mulloy
 *
 *         WTW Base Location DAO service API
 * 
 */
public interface LocationDataService {

    /**
     * Find Location by zip code.
     * 
     * @param zip
     * @return
     */
    Location findByZip(String zip);
}
