package com.durain.bootu.ordermgmt.order.service.exception;

import com.durain.bootu.ordermgmt.order.service.enums.ResultEnum;

public class OrderException extends RuntimeException {

	private static final long serialVersionUID = -2556146223563820640L;
	private Integer code;

	public OrderException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public OrderException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}

}
