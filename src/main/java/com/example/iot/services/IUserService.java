package com.example.iot.services;

import com.example.iot.entities.User;

public interface IUserService {

    User findById(String userId);

    User findByLogin(String login);

    void save(User user);

    boolean existsByLogin(String login);

}
