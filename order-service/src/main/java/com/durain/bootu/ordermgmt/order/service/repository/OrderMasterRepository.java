package com.durain.bootu.ordermgmt.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durain.bootu.ordermgmt.order.service.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>{

}
