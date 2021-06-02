package com.example.iot.configuration;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PARAMETER})
@AuthenticationPrincipal
@Documented
public @interface CurrentUser {
}
