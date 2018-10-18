package com.durain.bootu.ordermgmt.order.service.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.durain.bootu.ordermgmt.order.service.utils.JsonUtil;
import com.durain.bootu.product.game.common.GameInfoResponse;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GameInfoReceiver {

	private static final String GAME_STOCK_TEMPLATE = "GAME_STOCK_%s";

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@RabbitListener(queuesToDeclare = @Queue("DurainGameInfoQueue"))
	public void gameInfoQueueProcess(String message) {
		List<GameInfoResponse> gameInfoResponseList = new ArrayList<>();
		gameInfoResponseList = (List<GameInfoResponse>) JsonUtil.fromJson(message,
				new TypeReference<List<GameInfoResponse>>() {
				});
		log.info("Received message from [{}] : [{}]", "DurainGameInfoQueue", gameInfoResponseList);

		for (GameInfoResponse gameInfoResponse : gameInfoResponseList) {
			// Store latest stock into redis
			stringRedisTemplate.opsForValue().set(String.format(GAME_STOCK_TEMPLATE, gameInfoResponse.getGameId()),
					String.valueOf(gameInfoResponse.getGameStock()));
		}
	}
}
