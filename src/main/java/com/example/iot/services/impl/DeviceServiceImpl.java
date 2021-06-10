package com.example.iot.services.impl;

import com.example.iot.dao.IDeviceDao;
import com.example.iot.entities.Device;
import com.example.iot.services.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private IDeviceDao iDeviceDao;

    @Override
    public void save(Device device) {
        iDeviceDao.save(device);
    }

    @Override
    public Device findById(String id) {
        return iDeviceDao.findById(id).orElse(null);
    }

    @Override
    public Device findByMacAddress(String macAddress) {
        return iDeviceDao.findByMacAddress(macAddress).orElse(null);
    }

    @Override
    public List<Device> findAll() {
        return iDeviceDao.findAll();
    }

    @Override
    public List<Device> findByUserId(String userId) {
        return iDeviceDao.findByUserId(userId);
    }

    @Override
    public void deleteById(String id) {
        iDeviceDao.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllByMacAddress(String macAddress) {
        iDeviceDao.deleteAllByMacAddress(macAddress);
    }

    @Override
    public boolean existsByMacAddress(String macAddress) {
        return iDeviceDao.existsByMacAddress(macAddress);
    }
}
