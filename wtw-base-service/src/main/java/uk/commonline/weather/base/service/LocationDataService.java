package uk.commonline.weather.base.service;

import uk.commonline.weather.model.Location;

public interface LocationDataService {

	Location findByZip(String zip);
}
