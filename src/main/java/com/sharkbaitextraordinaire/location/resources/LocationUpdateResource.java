package com.sharkbaitextraordinaire.location.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path(value = "/location")
public class LocationUpdateResource {

	@GET
	public String getLocation() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String,Object> geojson = new HashMap<String,Object>();
		Map<String,Object> geometry = new HashMap<String,Object>();
		Map<String,Object> properties = new HashMap<String,Object>();
		geojson.put("type", "Feature");
		geometry.put("type", "Point");
		geometry.put("coordinates", new double[] { 45.523, -122.676 });
		geojson.put("geometry", geometry);
		geojson.put("properties", properties);
		
		try {
			return mapper.writeValueAsString(geojson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "beep boop";
	}

	
//	Example GeoJSON point
//	 {
//	  "type": "Feature",
//	  "geometry": {
//	    "type": "Point",
//	    "coordinates": [45.523, -122.676]
//	  },
//	  "properties": {
//	    "name": "Dinagat Islands"
//	  }
//	}
	
}
