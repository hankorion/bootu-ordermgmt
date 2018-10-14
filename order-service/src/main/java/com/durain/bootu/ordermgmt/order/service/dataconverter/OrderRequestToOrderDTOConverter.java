package com.durain.bootu.ordermgmt.order.service.dataconverter;

import java.util.ArrayList;
import java.util.List;

import com.durain.bootu.ordermgmt.order.service.dataobject.OrderDetails;
import com.durain.bootu.ordermgmt.order.service.dto.OrderDTO;
import com.durain.bootu.ordermgmt.order.service.enums.ResultEnum;
import com.durain.bootu.ordermgmt.order.service.exception.OrderException;
import com.durain.bootu.ordermgmt.order.service.requestobject.OrderRequestObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRequestToOrderDTOConverter {

	public static OrderDTO convert(OrderRequestObject orderReqObj) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerAddress(orderReqObj.getAddress());
		orderDTO.setBuyerName(orderReqObj.getName());
		orderDTO.setBuyerPhone(orderReqObj.getPhone());
		orderDTO.setBuyerOpenid(orderReqObj.getOpenid());

		List<OrderDetails> orderDetailsList = new ArrayList<>();
		Gson gson = new Gson();
		try {
			orderDetailsList = gson.fromJson(orderReqObj.getItems(), new TypeToken<List<OrderDetails>>() {
			}.getType());
		} catch (Exception e) {
			log.error("Order Convert error, string{}", orderReqObj.getItems());
			throw new OrderException(ResultEnum.PARAM_ERROR);
		}

		orderDTO.setOrderDetailsList(orderDetailsList);

		return orderDTO;
	}

}
