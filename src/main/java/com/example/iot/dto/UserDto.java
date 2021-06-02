package com.example.iot.dto;

import com.example.iot.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty("name")
    private String login;

    private String password;

    @JsonProperty("confirm_password")
    private String confirmPassword;

    public static User fromDtoToEntity(UserDto dto) {
        User entity = new User();
        entity.setId(new ObjectId().toString());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
