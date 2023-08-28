package com.auth.auth;

import com.core.core.CoreRoot;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {CoreRoot.class, AuthRoot.class, TestConfiguration.class})
public class TestConfiguration {
}
