package com.durain.bootu.ordermgmt.order.service.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderResultVO {

	@JsonProperty("orderId")
	private String orderId;

}
