package com.aws.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AwsCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsCloudApplication.class, args);
	}

}
