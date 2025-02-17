package com.esmt.product_service;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {
	@Autowired
	@Lazy
	private EurekaClient eurekaClient;

//	@Value("${spring.application.name}")
//	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
