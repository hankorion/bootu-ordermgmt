package com.durain.bootu.ordermgmt.order.service.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
	PARAM_ERROR(5001, "Order form error"), CART_EPMTY(5002, "Cart is empty"), ORDER_NOT_FOUND(5003, "Order not found"),
	ORDER_STATUS_ERROR(5004, "Order status error"), ORDER_DETAILS_NOT_FOUND(5005, "Order details not found"),;

	private Integer code;

	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

}
