package com.example.iot.services;

import com.example.iot.entities.Device;

import java.util.List;

public interface IDeviceService {

    void save(Device device);

    Device findById(String id);

    Device findByMacAddress(String macAddress);

    List<Device> findAll();

    void deleteById(String id);
}
