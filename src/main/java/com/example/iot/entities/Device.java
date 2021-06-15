package com.example.iot.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("MqttHost")
    private String mqttHost;

    @JsonProperty("MqttPort")
    private String mqttPort;

    @JsonProperty("MqttClient")
    private String mqttClient;

    @JsonProperty("Topic")
    private String mqttTopic;

    @JsonProperty("SSId")
    private String accessPoint;

    @JsonProperty("Hostname")
    private String hostName;

    @JsonProperty("IPAddress")
    private String ipAddress;

    @JsonProperty("DeviceName")
    private String name;

    private String username;

    private String password;

    @JsonProperty("Mac")
    private String macAddress;

    @JsonProperty("Position")
    private String position;

    @JsonProperty("Name1")
    private String name1;

    @JsonProperty("Name2")
    private String name2;

    @JsonProperty("IncludeAllOff")
    private Boolean includeAllOff;

    @JsonProperty("ProtectedFromPoweringOn")
    private Boolean protectedFromPoweringOn;

    @JsonProperty("ProtectedFromPoweringOff")
    private Boolean protectedFromPoweringOff;
}
