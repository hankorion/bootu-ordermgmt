package com.durain.bootu.ordermgmt.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


// @SpringBootApplication
// @EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.durain.bootu.product.game.client")
@ComponentScan(basePackages = "com.durain.bootu")
@SpringCloudApplication
@EnableHystrixDashboard
public class BootuOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootuOrderApplication.class, args);
	}
}
