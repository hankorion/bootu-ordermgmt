package com.durain.bootu.ordermgmt.order.service.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.durain.bootu.ordermgmt.order.service.service.GameGrpcService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class OrderHystrixController {

	@Autowired
	GameGrpcService gameGrpcService;
	
	@HystrixCommand(fallbackMethod = "getProductInfoListFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") })
	@GetMapping("/getProductInfoList")
	public String getProductInfoList() {

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject("http://localhost:8081/games/listFromOrder",
				Arrays.asList("Blizzard Entertainment - World of Warcraft"), String.class);
	}

	private String getProductInfoListFallback() {
		return "game service is busy";
	}

	private String defaultFallback() {
		return "service is busy";
	}
	
	@GetMapping("/grpcAllGames")
	public void getGallGames() {
		gameGrpcService.listGames();
	}
}
