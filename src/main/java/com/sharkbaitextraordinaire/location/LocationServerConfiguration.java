package com.sharkbaitextraordinaire.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class LocationServerConfiguration extends Configuration {

    @JsonProperty
    private OwntracksMqttClientConfiguration owntracksMqttClient = new OwntracksMqttClientConfiguration();

    public OwntracksMqttClientConfiguration getOwntracksMqttClientConfiguration() {
        return owntracksMqttClient;
    }
}
