package com.durain.bootu.ordermgmt.order.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durain.bootu.product.game.client.GameServiceClient;
import com.durain.bootu.product.game.common.GameDescreaseStockRequest;
import com.durain.bootu.product.game.common.GameInfoResponse;

@RestController
public class GameClientController {

	@Autowired
	private GameServiceClient gameServiceClient;

	@GetMapping("/getGameMsg")
	public String getGameMsg() {
		return gameServiceClient.gameMsg();
	}

	@GetMapping("/listFromOrder")
	public String listFromOrder() {

		List<GameInfoResponse> gameInfoList = gameServiceClient
				.listFromOrder(Arrays.asList("Blizzard Entertainment - Diablo III"));

		return "" + gameInfoList.size();
	}

	@GetMapping("/gameDecreaseStock")
	public String gameDecreaseStock() {
		GameDescreaseStockRequest gameDescreaseStockRequest = new GameDescreaseStockRequest("Blizzard Entertainment - Diablo III", 1);
		gameServiceClient.decreaseGameStock(Arrays.asList(gameDescreaseStockRequest));
		return "ok";
	}
}
