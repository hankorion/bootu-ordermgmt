package com.durain.bootu.ordermgmt.order.service.service;

import org.springframework.stereotype.Service;

import com.durain.bootu.product.game.grpc.lib.GamesListResponse;

@Service
public interface GameGrpcOrProtoBufService {

	public void listGames();

	public GamesListResponse listGamesProtobuf();

}