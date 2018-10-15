package com.durain.bootu.ordermgmt.order.service.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durain.bootu.ordermgmt.order.service.dto.OrderDTO;
import com.durain.bootu.ordermgmt.order.service.message.StreamClient;

@RestController
@RequestMapping("/env")
@RefreshScope
public class EnvController {

	@Autowired
	private StreamClient streamClient;

	@Value("${env}")
	private String env;

	@GetMapping("/print")
	public String print() {
		return env;
	}

//	@GetMapping("/sendMessageToStream")
//	public void sendMsg() {
//		streamClient.output().send(MessageBuilder.withPayload("now " + new Date()).build());
//	}
//	
	@GetMapping("/sendMessageToStream")
	public void sendOrder() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId("1539493434194825026");
		
		streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
	}

}
