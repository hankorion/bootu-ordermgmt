package com.durain.bootu.ordermgmt.order.service.service;

import org.springframework.stereotype.Service;

import com.durain.bootu.product.game.grpc.lib.GameServicesGrpc;
import com.durain.bootu.product.game.grpc.lib.GamesListRequest;
import com.durain.bootu.product.game.grpc.lib.GamesListResponse;

import io.grpc.Channel;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;

@Service
@Slf4j
public class GameGrpcService {

	@GrpcClient("durain-games")
	private Channel serverChannel;

	public void listGames() {
		GameServicesGrpc.GameServicesBlockingStub stub = GameServicesGrpc.newBlockingStub(serverChannel);
		GamesListResponse gamesListResp = stub.gamesList(GamesListRequest.newBuilder().build());
		// log.info(" GameGrpcService Client --> {}", gamesListResp);
	}

}