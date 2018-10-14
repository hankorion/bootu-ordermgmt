package com.durain.bootu.ordermgmt.order.service.dto;

import lombok.Data;

@Data
public class CartDTO {

	private String gameId;

	private Integer gameQuantity;

	public CartDTO() {
	}

	public CartDTO(String gameId, Integer gameQuantity) {
		super();
		this.gameId = gameId;
		this.gameQuantity = gameQuantity;
	}
}
