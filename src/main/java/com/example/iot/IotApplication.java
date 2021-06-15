package com.example.iot;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Objects;

@SpringBootApplication
public class IotApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        DefaultMqttPahoClientFactory defaultMqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("app-watertec");
        options.setPassword("app-watertec".toCharArray());
        defaultMqttPahoClientFactory.setConnectionOptions(options);
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://138.197.130.191:1883", "app-watertec", defaultMqttPahoClientFactory,
                        "tasmota/discovery/DC4F22AD0279/config", "tele/4chPro2/SENSOR", "tele/4chPro2/STATE",
                        "tasmota/discovery/84CCA89C3376/config", "tele/tasmota_9C3376/SENSOR", "tele/tasmota_9C3376/STATE");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Autowired
    SimpMessagingTemplate template;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            System.out.println(message.getPayload());
            System.out.println(message.getHeaders());
            if (Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")).toString().contains("4chPro2") || Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")).toString().contains("DC4F22AD0279")) {
                System.out.println("device 1");
                template.convertAndSend("/topic/4chPro2", message.getPayload());
            }
            if (Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")).toString().contains("tasmota_9C3376") || Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")).toString().contains("84CCA89C3376")) {
                System.out.println("device 2");
                template.convertAndSend("/topic/tasmota_9C3376", message.getPayload());
            }
        };
    }
}
