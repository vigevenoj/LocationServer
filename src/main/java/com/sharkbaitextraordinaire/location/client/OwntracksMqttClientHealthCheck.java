package com.sharkbaitextraordinaire.location.client;

import com.codahale.metrics.health.HealthCheck;

public class OwntracksMqttClientHealthCheck extends HealthCheck{

    private final OwntracksMqttClient client;

    public OwntracksMqttClientHealthCheck(OwntracksMqttClient client) {
        this.client = client;
    }

    @Override
    protected Result check() throws Exception {
        if (client.client.isConnected()) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Disconnected from MQTT broker " + client.client.getServerURI());
        }

    }
}
