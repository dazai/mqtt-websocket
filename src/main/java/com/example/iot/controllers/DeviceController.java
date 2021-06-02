package com.example.iot.controllers;

import com.example.iot.models.SingleFieldRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @PostMapping("/add")
    public ResponseEntity<?> addDevice(@RequestBody SingleFieldRequest macAddress) {
        return ResponseEntity.ok("kvf");
    }
}
