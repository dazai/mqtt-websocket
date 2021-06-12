package com.example.iot.dao;

import com.example.iot.entities.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface IDeviceDao extends MongoRepository<Device, String> {

    Optional<Device> findByMacAddress(String macAddress);

    List<Device> findByUserId(String userId);

    void deleteAllByMacAddress(String macAddress);

    boolean existsByMacAddress(String macAddress);
}
