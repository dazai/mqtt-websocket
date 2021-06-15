package com.example.iot.controllers;

import com.example.iot.models.SingleFieldRequest;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/publish")
public class PublishController {

    @PostMapping("/{device}")
    public ResponseEntity<?> publish(@RequestBody SingleFieldRequest msg, @PathVariable String device) throws MqttException {
        System.out.println("####################");
        IMqttClient mqttClient = new MqttClient("tcp://138.197.130.191:1883", "watertec-04");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("watertec-04");
        options.setPassword("watertec-04".toCharArray());
        mqttClient.connect(options);
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getValue().getBytes());
        message.setQos(0);
        mqttClient.publish("cmnd/"+device+"/POWER", message);
        return ResponseEntity.ok("done");
    }
}
