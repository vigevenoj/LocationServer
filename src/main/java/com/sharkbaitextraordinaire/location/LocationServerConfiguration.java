package com.sharkbaitextraordinaire.location;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharkbaitextraordinaire.location.management.ManagementConfiguration;
//import com.sharkbaitextraordinaire.location.management.ManagementConfiguration;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class LocationServerConfiguration extends Configuration {

    @JsonProperty
    private OwntracksMqttClientConfiguration owntracksMqttClient = new OwntracksMqttClientConfiguration();

    public OwntracksMqttClientConfiguration getOwntracksMqttClientConfiguration() {
        return owntracksMqttClient;
    }
    
    // management configuration includes whether tracking is enabled or not
    private ManagementConfiguration managementConfiguration;
    
    public ManagementConfiguration getManagementConfiguration() {
    	return managementConfiguration;
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
