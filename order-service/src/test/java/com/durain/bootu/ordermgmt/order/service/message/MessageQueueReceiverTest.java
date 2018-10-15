package com.durain.bootu.ordermgmt.order.service.message;

import java.util.Date;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.durain.bootu.ordermgmt.order.service.BootuOrderApplicationTest;

@Component
public class MessageQueueReceiverTest extends BootuOrderApplicationTest {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Test
	public void t_01_MessageQueueReceiver() {
		amqpTemplate.convertAndSend("bootuOrderQueue", "now " + new Date());
	}

}
