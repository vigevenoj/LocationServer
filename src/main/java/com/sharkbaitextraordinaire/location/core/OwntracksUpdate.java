package com.sharkbaitextraordinaire.location.core;

public class OwntracksUpdate {
	
	String _type;
	String lat;
	String lon;
	String acc;
	String batt;
	String tst;
	String event;
	
	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getBatt() {
		return batt;
	}

	public void setBatt(String batt) {
		this.batt = batt;
	}

	public String getTst() {
		return tst;
	}

	public void setTst(String tst) {
		this.tst = tst;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	// example update
	// {"_type": "location", "lat": "45.5367495", "lon": "-122.6217988", "tst": "1437014122", "acc": "50.0", "batt": "81", "event": "enter"}
	
//	public JSONObject toGeoJSONO(OwntracksUpdate update) {
//		
//	}

}
