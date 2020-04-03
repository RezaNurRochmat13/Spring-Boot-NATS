package com.spring.boot.natsstreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NatsStreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NatsStreamsApplication.class, args);
	}

}
