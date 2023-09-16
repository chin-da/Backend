package com.chinda.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.chinda.iam", "com.chinda.common"})
@EnableFeignClients("com.chinda.iam")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EntityScan("com.chinda.iam_shared_kernel.*")
public class IAMApplication {

	public static void main(String[] args) {
		SpringApplication.run(IAMApplication.class, args);
	}

}
