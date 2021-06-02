package com.example.iot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Device implements Serializable {

    @Id
    private String id;

    private String userId;

    private String mqttHost;

    private String mqttPort;

    private String mqttClient;

    private String mqttTopic;

    private String accessPoint;

    private String hostName;

    private String macAddress;
}
