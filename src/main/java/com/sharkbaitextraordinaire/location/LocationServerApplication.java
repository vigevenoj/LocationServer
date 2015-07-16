package com.sharkbaitextraordinaire.location;

import com.sharkbaitextraordinaire.location.client.OwntracksMqttClient;
import com.sharkbaitextraordinaire.location.resources.LocationUpdateResource;

import io.dropwizard.Application;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;

public class LocationServerApplication extends Application<LocationServerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new LocationServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "LocationServer";
    }

    @Override
    public void initialize(final Bootstrap<LocationServerConfiguration> bootstrap) {
        // TODO: application initialization
    	AssetsBundle assetsBundle = new AssetsBundle("/assets/", "/", "index.htm", "static");
    	bootstrap.addBundle(assetsBundle);
    }

    @Override
    public void run(final LocationServerConfiguration configuration,
                    final Environment environment) {
    	final Managed owntracksMqttClient = new OwntracksMqttClient();
    	environment.lifecycle().manage(owntracksMqttClient);
    	environment.jersey().register(new LocationUpdateResource());
    }

}
