package com.durain.bootu.ordermgmt.order.service.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

	String INPUTQ = "DurainBootuOrderMessageInput";
	String OUTPUTQ = "DurainBootuOrderMessageOutput";
	
	@Input(StreamClient.INPUTQ)
	SubscribableChannel input();
	
	@Output(StreamClient.OUTPUTQ)
	MessageChannel output();
}
