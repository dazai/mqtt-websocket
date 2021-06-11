package com.example.iot.controllers;

import com.example.iot.configuration.CurrentUser;
import com.example.iot.entities.Device;
import com.example.iot.entities.User;
import com.example.iot.exceptions.BadRequestException;
import com.example.iot.models.RestResponse;
import com.example.iot.models.SingleFieldRequest;
import com.example.iot.models.UserPrincipal;
import com.example.iot.services.IDeviceService;
import com.example.iot.services.IUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private IDeviceService iDeviceService;

    @Autowired
    private IUserService iUserService;

    @PostMapping("/add")
    public ResponseEntity<?> addDevice(@RequestBody Device device, @CurrentUser UserPrincipal userPrincipal) {
        System.out.println(device);
        if (iDeviceService.existsByMacAddress(device.getMacAddress())) {
            throw new BadRequestException("Device already in use");
        }
        device.setUserId(userPrincipal.getId());
        device.setId(ObjectId.get().toString());
        User user = iUserService.findById(userPrincipal.getId());
        device.setUsername(user.getLogin());
        device.setPassword(user.getPassword());
        iDeviceService.save(device);
        return ResponseEntity.ok(device);
    }

    @GetMapping
    public ResponseEntity<?> listDevices(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(iDeviceService.findByUserId(userPrincipal.getId()));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDevice(@RequestBody SingleFieldRequest macAddress) {
        iDeviceService.deleteAllByMacAddress(macAddress.getValue());
        return ResponseEntity.ok(new RestResponse(true, "Device deleted successfully"));
    }
}
