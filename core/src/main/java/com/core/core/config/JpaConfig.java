package com.core.core.config;

import com.core.core.CoreRoot;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = {CoreRoot.class})
@EnableJpaRepositories(basePackageClasses = {CoreRoot.class})
@EnableJpaAuditing
public class JpaConfig {

}
