package com.example.iot.controllers;

import com.example.iot.dto.UserDto;
import com.example.iot.entities.User;
import com.example.iot.exceptions.BadRequestException;
import com.example.iot.models.AuthenticationRequest;
import com.example.iot.models.AuthenticationResponse;
import com.example.iot.services.CustomUserDetailsService;
import com.example.iot.services.IUserService;
import com.example.iot.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        if (!StringUtils.hasLength(userDto.getConfirmPassword()) || !StringUtils.hasLength(userDto.getPassword())) {
            throw new BadRequestException("password and confirm password can not be null or empty");
        }

        if (!userDto.getConfirmPassword().equals(userDto.getPassword())) {
            throw new BadRequestException("password and confirm password must be the same");
        }

        if (iUserService.existsByLogin(userDto.getLogin())) {
            throw new BadRequestException("User already exists");
        }

        User user = UserDto.fromDtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        iUserService.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(), authenticationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.status(200).body(new AuthenticationResponse(token));
    }
}
