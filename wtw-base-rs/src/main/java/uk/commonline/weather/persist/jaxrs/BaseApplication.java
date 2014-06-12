package uk.commonline.weather.persist.jaxrs;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("webresources")
public class BaseApplication extends ResourceConfig {
    public BaseApplication() {
        packages("uk.commonline.weather.persist.jaxrs;uk.commonline.weather.model;org.codehaus.jackson.jaxrs");
    }
}