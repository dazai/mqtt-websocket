package com.example.iot.dao;

import com.example.iot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDao extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}
