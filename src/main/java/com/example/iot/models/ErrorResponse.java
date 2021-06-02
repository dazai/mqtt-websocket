package com.example.iot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @JsonProperty("time_stamp")
    private String timeStamp;

    private int status;

    private String error;

    private String message;

    private String path;
}
