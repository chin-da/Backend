package com.chinda.ranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.chinda.ranking", "com.chinda.common", "com.chinda.iam_shared_kernel"})
@EntityScan({"com.chinda.iam_shared_kernel.*", "com.chinda.ranking.*"})
public class RankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RankingApplication.class, args);
	}

}
