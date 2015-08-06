package com.sharkbaitextraordinaire.location;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class LocationServerConfiguration extends Configuration {

    @JsonProperty
    private OwntracksMqttClientConfiguration owntracksMqttClient = new OwntracksMqttClientConfiguration();

    public OwntracksMqttClientConfiguration getOwntracksMqttClientConfiguration() {
        return owntracksMqttClient;
    }
    
    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();
    
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
