package com.example.iot.dao;

import com.example.iot.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDeviceDao extends JpaRepository<Device, String> {

    Optional<Device> findByMacAddress(String macAddress);
}
