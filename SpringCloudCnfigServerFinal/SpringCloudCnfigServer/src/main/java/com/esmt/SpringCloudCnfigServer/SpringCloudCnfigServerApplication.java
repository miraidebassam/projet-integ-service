package com.esmt.SpringCloudCnfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
@RestController
public class SpringCloudCnfigServerApplication {

	@GetMapping("/example")
	public String example() {
		return "Ceci est un exemple!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCnfigServerApplication.class, args);
	}

}
