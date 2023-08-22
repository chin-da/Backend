package com.auth.auth.config;

import com.auth.auth.AuthRoot;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = {AuthRoot.class})
public class AuthConfig {
}
