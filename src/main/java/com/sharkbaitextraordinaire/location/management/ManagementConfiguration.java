package com.sharkbaitextraordinaire.location.management;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagementConfiguration {

	private boolean trackingEnabled = true;
	
	@JsonProperty
	public void setTrackingEnabled(boolean trackingEanbled) {
		this.trackingEnabled = trackingEnabled;
	}
	
	@JsonProperty
	public boolean getTrackingEnabled() {
		return trackingEnabled;
	}
}
