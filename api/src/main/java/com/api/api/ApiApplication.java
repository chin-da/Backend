package com.api.api;

import com.auth.auth.AuthRoot;
import com.common.common.config.CommonRoot;
import com.core.core.CoreRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {
        CoreRoot.class,
        AuthRoot.class,
        CommonRoot.class,
        ApiApplication.class
})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
