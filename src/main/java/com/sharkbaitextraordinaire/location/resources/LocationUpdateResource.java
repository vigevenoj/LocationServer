package com.sharkbaitextraordinaire.location.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(value = "/location")
public class LocationUpdateResource {

	@GET
	public String getLocation() {
		return "beep boop";
	}
	
//	Example GeoJSON point
//	 {
//	  "type": "Feature",
//	  "geometry": {
//	    "type": "Point",
//	    "coordinates": [125.6, 10.1]
//	  },
//	  "properties": {
//	    "name": "Dinagat Islands"
//	  }
//	}
	
}
