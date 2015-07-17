package com.sharkbaitextraordinaire.location.client;

import com.sharkbaitextraordinaire.location.OwntracksMqttClientConfiguration;
import io.dropwizard.lifecycle.Managed;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class OwntracksMqttClient implements MqttCallback, Managed {
	
	private final Logger LOGGER = LoggerFactory.getLogger(OwntracksMqttClient.class);

    private OwntracksMqttClientConfiguration owntracksMqttClientConfiguration;
	
	MqttClient client;
	MqttConnectOptions connectionOptions;

    public OwntracksMqttClient(OwntracksMqttClientConfiguration owntracksMqttClientConfiguration){
        this.owntracksMqttClientConfiguration = owntracksMqttClientConfiguration;
    }

	@Override
	public void connectionLost(Throwable arg0) {
		// fail healthcheck
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// no-op because we don't deliver anything
		
	}

	@Override
	public void messageArrived(String topicString, MqttMessage message) throws Exception {
		System.out.println(new String(message.getPayload()));
		// should log message arrival
		// Also put message into global scope so we can fetch latest message when requested
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
                LOGGER.warn("connected to mqtt broker for owntracks");
            } else {
                LOGGER.warn("NOT CONNECTED to mqtt broker");
            }

            MqttTopic topic = client.getTopic(owntracksMqttClientConfiguration.getTopic());

            int subQoS = 0;
            client.subscribe(topic.getName(), subQoS);
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
