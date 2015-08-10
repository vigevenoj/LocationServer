package com.sharkbaitextraordinaire.location.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharkbaitextraordinaire.location.OwntracksMqttClientConfiguration;
import com.sharkbaitextraordinaire.location.core.OwntracksUpdate;
import com.sharkbaitextraordinaire.location.db.OwntracksUpdateDAO;
import io.dropwizard.lifecycle.Managed;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class OwntracksMqttClient implements MqttCallback, Managed {
	
	private final Logger LOGGER = LoggerFactory.getLogger(OwntracksMqttClient.class);

    private OwntracksMqttClientConfiguration owntracksMqttClientConfiguration;
    private OwntracksUpdateDAO owntracksUpdateDAO;
	
	MqttClient client;
	MqttConnectOptions connectionOptions;

    public OwntracksMqttClient(OwntracksMqttClientConfiguration owntracksMqttClientConfiguration, OwntracksUpdateDAO dao){
        this.owntracksMqttClientConfiguration = owntracksMqttClientConfiguration;
        this.owntracksUpdateDAO = dao;
    }

    public MqttClient getClient() {
        return client;
    }

	@Override
	public void connectionLost(Throwable arg0) {
        LOGGER.error("MQTT broker connection lost");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// no-op because we don't deliver anything

	}

	@Override
	public void messageArrived(String topicString, MqttMessage message) throws Exception {
		String payload = new String(message.getPayload());
        // should log message arrival
        LOGGER.error("new message from broker:");
        LOGGER.error(payload);

        try {
            ObjectMapper mapper = new ObjectMapper();
            OwntracksUpdate update = mapper.readValue(payload, OwntracksUpdate.class);
            LOGGER.error(update.toString());
            owntracksUpdateDAO.insert(update.get_type(), update.getLat(), update.getLon(), update.getAcc(), update.getTst(), update.getBatt());

            OwntracksUpdate latest = owntracksUpdateDAO.findLatest();
            LOGGER.error(latest.getLon() + ", " + latest.getLat());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void start() {

		String clientID = owntracksMqttClientConfiguration.getClientID();
		String brokerUrl = owntracksMqttClientConfiguration.getBrokerUrl();
		
		connectionOptions = new MqttConnectOptions();
		connectionOptions.setKeepAliveInterval(30);
		connectionOptions.setUserName(owntracksMqttClientConfiguration.getUserName());
		connectionOptions.setPassword(owntracksMqttClientConfiguration.getPassword().toCharArray());
		
		Properties sslProps = new Properties();
		sslProps.setProperty("com.ibm.ssl.protocol", owntracksMqttClientConfiguration.getSslProtocol());
		sslProps.setProperty("com.ibm.ssl.trustStore", owntracksMqttClientConfiguration.getTrustStore());
		sslProps.setProperty("com.ibm.ssl.trustStorePassword", owntracksMqttClientConfiguration.getTrustStorePassword());
		
		connectionOptions.setSSLProperties(sslProps);
		
		try {
			client = new MqttClient(brokerUrl, clientID);
			client.setCallback(this);
            client.connect(connectionOptions);

            if (client.isConnected()) {
                LOGGER.error("connected to mqtt broker for owntracks");
                MqttTopic topic = client.getTopic(owntracksMqttClientConfiguration.getTopic());

                int subQoS = 0;
                client.subscribe(topic.getName(), subQoS);

            } else {
                LOGGER.error("NOT CONNECTED to mqtt broker");
            }


		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			client.disconnect();
			client.close();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
