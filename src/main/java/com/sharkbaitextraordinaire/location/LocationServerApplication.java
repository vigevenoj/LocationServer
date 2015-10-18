package com.sharkbaitextraordinaire.location;

import com.sharkbaitextraordinaire.location.client.OwntracksMqttClient;
import com.sharkbaitextraordinaire.location.client.OwntracksMqttClientHealthCheck;
import com.sharkbaitextraordinaire.location.db.ManagementDAO;
import com.sharkbaitextraordinaire.location.db.OwntracksUpdateDAO;
import com.sharkbaitextraordinaire.location.resources.LocationUpdateResource;
import com.sharkbaitextraordinaire.location.resources.OwntracksResource;
import com.sharkbaitextraordinaire.location.resources.management.ManagementResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


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
    	final DBI dbi = new DBIFactory().build(environment, configuration.getDataSourceFactory(), "database");
    	final OwntracksUpdateDAO otdao = dbi.onDemand(OwntracksUpdateDAO.class);
        otdao.createTableIfNotExists();
        final ManagementDAO mgmtdao = dbi.onDemand(ManagementDAO.class);
        mgmtdao.createTableIfNotExists();
        
        
        environment.jersey().register(new LocationUpdateResource());
        environment.jersey().register(new OwntracksResource(otdao));
        environment.jersey().register(new ManagementResource(otdao, mgmtdao));

        final Managed owntracksMqttClient = new OwntracksMqttClient(configuration.getOwntracksMqttClientConfiguration(), otdao, mgmtdao);
        environment.lifecycle().manage(owntracksMqttClient);

        environment.healthChecks().register("broker", new OwntracksMqttClientHealthCheck(((OwntracksMqttClient)owntracksMqttClient)));
    }

}
