package com.durain.bootu.ordermgmt.order.service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durain.bootu.ordermgmt.order.service.VO.OrderResultVO;
import com.durain.bootu.ordermgmt.order.service.VO.ResultVO;
import com.durain.bootu.ordermgmt.order.service.dataconverter.OrderRequestToOrderDTOConverter;
import com.durain.bootu.ordermgmt.order.service.dto.OrderDTO;
import com.durain.bootu.ordermgmt.order.service.enums.ResultEnum;
import com.durain.bootu.ordermgmt.order.service.exception.OrderException;
import com.durain.bootu.ordermgmt.order.service.requestobject.OrderRequestObject;
import com.durain.bootu.ordermgmt.order.service.service.OrderService;
import com.durain.bootu.ordermgmt.order.service.utils.ResultVOUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/new")
	public ResultVO<OrderResultVO> createOrder(@Valid OrderRequestObject orderReqObj, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("Create order error, orderRequestObject={}", orderReqObj);
			throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
					bindingResult.getFieldError().getDefaultMessage());
		}

		OrderDTO orderDTO = OrderRequestToOrderDTOConverter.convert(orderReqObj);

		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailsList())) {
			log.error("Order cart empty error, orderRequestObject={}", orderReqObj);
			throw new OrderException(ResultEnum.CART_EPMTY);
		}

		OrderDTO orderResultDTO = orderService.createOrder(orderDTO);
		OrderResultVO orderResultVO = new OrderResultVO();
		orderResultVO.setOrderId(orderResultDTO.getOrderId());
		return ResultVOUtil.success(orderResultVO);
	}

	@PostMapping("/complete")
	public ResultVO<OrderResultVO> completeOrder(@RequestParam("orderId") String orderId) {
		return ResultVOUtil.success(orderService.completeOrder(orderId));
	}

}
