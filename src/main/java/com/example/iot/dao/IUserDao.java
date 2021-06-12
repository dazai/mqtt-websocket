package com.example.iot.dao;

import com.example.iot.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface IUserDao extends MongoRepository<User, String> {

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}
