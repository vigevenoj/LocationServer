package com.sharkbaitextraordinaire.location.client;

import java.util.Properties;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.dropwizard.lifecycle.Managed;

public class OwntracksMqttClient implements MqttCallback, Managed {
	
	private final Logger LOGGER = LoggerFactory.getLogger(OwntracksMqttClient.class);
	
	MqttClient client;
	MqttConnectOptions connectionOptions;

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
		String clientID = "";
		String brokerUrl = "";
		
		connectionOptions = new MqttConnectOptions();
		connectionOptions.setKeepAliveInterval(30);
		connectionOptions.setUserName("");
		connectionOptions.setPassword("".toCharArray());
		
		Properties sslProps = new Properties();
		sslProps.setProperty("com.ibm.ssl.protocol", "");
		sslProps.setProperty("com.ibm.ssl.trustStore", "");
		sslProps.setProperty("com.ibm.ssl.trustStorePassword", "");
		
		connectionOptions.setSSLProperties(sslProps);
		
		try {
			client = new MqttClient(brokerUrl, clientID);
			client.setCallback(this);
		} catch (MqttException e) {
			// TODO something
		}
		
		MqttTopic topic = client.getTopic("");
		
		if (true) {
			// TODO should probably have a shutdown method?
			try {
				int subQoS = 0;
				client.subscribe(topic.getName(), subQoS);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
