package com.durain.bootu.ordermgmt.order.service.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.durain.bootu.ordermgmt.order.service.dto.OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

//	@StreamListener(value = StreamClient.INPUTQ)
//	public void processOutput(Object message) {
//		log.info("StreamReceiver: {}", message);
//	}
	
	@StreamListener(StreamClient.INPUTQ)
	public void processOrderDto(OrderDTO message) {
		log.info("StreamReceiver: {}", message);
	}
}
