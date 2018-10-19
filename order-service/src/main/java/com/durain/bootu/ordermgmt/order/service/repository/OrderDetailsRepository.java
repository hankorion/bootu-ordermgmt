package com.durain.bootu.ordermgmt.order.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durain.bootu.ordermgmt.order.service.dataobject.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {
	
	List<OrderDetails> findByOrderId(String orderId);
}
