package com.api.api;

import com.auth.auth.AuthRoot;
import com.core.core.CoreRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {
        CoreRoot.class,
        AuthRoot.class,
        ApiApplication.class
})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
