package com.durain.bootu.ordermgmt.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.durain.bootu.product.game.client")
public class BootuOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootuOrderApplication.class, args);
	}
}
