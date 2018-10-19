package com.durain.bootu.ordermgmt.order.service.service;

import com.durain.bootu.ordermgmt.order.service.dto.OrderDTO;

public interface OrderService {
	public OrderDTO createOrder(OrderDTO orderDto);
	
	OrderDTO completeOrder(String orderId);
}
