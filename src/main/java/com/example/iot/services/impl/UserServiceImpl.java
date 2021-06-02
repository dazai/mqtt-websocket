package com.example.iot.services.impl;

import com.example.iot.dao.IUserDao;
import com.example.iot.entities.User;
import com.example.iot.exceptions.ResourceNotFoundException;
import com.example.iot.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public User findById(String userId) {
        return iUserDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @Override
    public User findByLogin(String login) {
        return iUserDao.findByLogin(login).orElseThrow(() -> new ResourceNotFoundException("User", "name", login));
    }

    @Override
    public void save(User user) {
        iUserDao.save(user);
    }

    @Override
    public boolean existsByLogin(String login) {
        return iUserDao.existsByLogin(login);
    }
}
