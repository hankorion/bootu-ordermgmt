package com.durain.bootu.ordermgmt.order.service.message;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageQueueReceiver {

	// Auto add to queue @RabbitListener(queuesToDeclare =
	// @Queue("bootuOrderQueue"))
	@RabbitListener(bindings = @QueueBinding(value = @Queue("bootuOrderQueue"), exchange = @Exchange("durain")))
	public void process(String message) {
		log.info("MessageQueueReceiver: {}", message);
	}
}
