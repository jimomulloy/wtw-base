package uk.commonline.weather.persist.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import uk.commonline.weather.model.Location;

public class Tester {
	public static void main(String[] args) {
		
		ClientConfig cc = new ClientConfig().register(new JacksonFeature());
	    Client client = ClientBuilder.newClient(cc);
		
		Location entity = client.target("http://localhost:8080/wtwbase")
	            .path("webresources/location/1")
	            .request(MediaType.APPLICATION_JSON)
	            .get(Location.class);

		System.out.println("Server response .... \n");
		System.out.println("!!Location:"+entity);
		
		GenericType<List<Location>> list = new GenericType<List<Location>>() {};
		 
		List<Location> entities = client.target("http://localhost:8080/wtwbase")
	            .path("webresources/location")
	            .request(MediaType.APPLICATION_JSON)
	            .get(list);

		System.out.println("Server response 2 .... \n");
		System.out.println("!!Locations:"+entities);
         
        // List<Music> listMusic = client.target("http://localhost:8080/RESTfulServices/rs/ArtisteNameBeginningBy/")
         //               .path("{beginBy}")
         //               .pathParam("beginBy", artistBeginBy)
         //               .request(MediaType.APPLICATION_XML)
         //               .get(listm);
         
         //return listMusic;
	  }

}
