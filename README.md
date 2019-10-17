# What is this?
This is a demo app to display my current run on a map, powered by owntracks, dropwizard, and leaflet.js. It connects to an MQTT broker and subscribes to owntracks updates on a specified topic. While running, the application serves clients a map with location updates, and updates the map every 30 seconds.

# Requirements
To build the project, you'll need maven3 and java (jdk8).

To run the application, you'll need
* Owntracks on a mobile device
* An MQTT broker, for Owntracks to communicate with
* java

# Configuration
The application is configured via a yaml file or via -D arguments to the java command starting the server. If you're using self-signed certificates or certificates not present in the default jdk truststore, you'll need to update the sbe-mqtt.keystore present in the src/resources directory. See commit d85f374430043bd2a7005e997398d1d833a381e4 for the previous mechanism for loading the keystore from the filesystem. See LocationServer.yml or heroku.yml in the project root for the full list of configuration options.

Currently: 
* displays an index page with a leaflet.js map centered roughly on Portland, OR
* has javascript to load latest location and re-center map onto latest location received from broker
* responds to requests to /api/location with a default location of 45.523, -122.676 or "beep boop" if an error occurs
* responds to requests to /api/rawupates with raw owntracks mqtt messages since application startup
* responds to requests to /api/rawupdates/since/{tst} with owntracks messages from broker since epoch timestamp (only messages after application startup)
* responds to requests to /api/rawupdates/latest with latest owntracks message from broker
* loops to check updated location every 30 seconds and adds new points to map

TODO:
* convert owntracks location messages to GeoJSON
* optionally fall back to loading keystore from filesystem
* add mechanism to pause adding points to the map
* add mechanism to clear the list of points without restarting
