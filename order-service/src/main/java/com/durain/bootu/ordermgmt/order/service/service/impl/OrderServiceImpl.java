package com.durain.bootu.ordermgmt.order.service.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durain.bootu.ordermgmt.order.service.dataobject.OrderDetails;
import com.durain.bootu.ordermgmt.order.service.dataobject.OrderMaster;
import com.durain.bootu.ordermgmt.order.service.dto.OrderDTO;
import com.durain.bootu.ordermgmt.order.service.enums.OrderStatusEnum;
import com.durain.bootu.ordermgmt.order.service.enums.PayStatusEnum;
import com.durain.bootu.ordermgmt.order.service.enums.ResultEnum;
import com.durain.bootu.ordermgmt.order.service.exception.OrderException;
import com.durain.bootu.ordermgmt.order.service.repository.OrderDetailsRepository;
import com.durain.bootu.ordermgmt.order.service.repository.OrderMasterRepository;
import com.durain.bootu.ordermgmt.order.service.service.OrderService;
import com.durain.bootu.ordermgmt.order.service.utils.UuidUtil;
import com.durain.bootu.product.game.client.GameServiceClient;
import com.durain.bootu.product.game.common.GameDescreaseStockRequest;
import com.durain.bootu.product.game.common.GameInfoResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMasterRepository orderMasterRepository;

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private GameServiceClient gameServiceClient;

	@Override
	@Transactional
	public OrderDTO createOrder(OrderDTO orderDto) {

		String orderId = UuidUtil.genUniqueKey();

		List<String> productIdList = orderDto.getOrderDetailsList().stream().map(OrderDetails::getProductId)
				.collect(Collectors.toList());

		List<GameInfoResponse> gameInfoList = gameServiceClient.listFromOrder(productIdList);

		BigDecimal orderAmt = new BigDecimal(BigInteger.ZERO);
		for (OrderDetails orderDtls : orderDto.getOrderDetailsList()) {
			for (GameInfoResponse gameInfo : gameInfoList) {
				if (gameInfo.getGameId().equals(orderDtls.getProductId())) {
					orderAmt = gameInfo.getGamePrice().multiply(new BigDecimal(orderDtls.getProductQuantity()))
							.add(orderAmt);
					BeanUtils.copyProperties(gameInfo, orderDtls);
					orderDtls.setOrderId(orderId);
					orderDtls.setDetailId(UuidUtil.genUniqueKey());
					orderDtls.setProductIcon(gameInfo.getGameIcon());
					orderDtls.setProductId(gameInfo.getGameId());
					orderDtls.setProductName(gameInfo.getGameName());
					orderDtls.setProductPrice(gameInfo.getGamePrice());
					orderDtls.setProductId(gameInfo.getGameId());

					orderDetailsRepository.save(orderDtls);
				}
			}

		}

		List<GameDescreaseStockRequest> cardDTOList = orderDto.getOrderDetailsList().stream()
				.map(e -> new GameDescreaseStockRequest(e.getProductId(), e.getProductQuantity()))
				.collect(Collectors.toList());
		gameServiceClient.decreaseGameStock(cardDTOList);
		orderDto.setOrderId(orderId);

		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDto, orderMaster);
		orderMaster.setOrderAmount(orderAmt);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.NEW.getCode());
		orderMasterRepository.save(orderMaster);

		return orderDto;
	}

	@Override
	@Transactional
	public OrderDTO completeOrder(String orderId) {
		Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
		if (!orderMasterOptional.isPresent()) {
			throw new OrderException(ResultEnum.ORDER_NOT_FOUND);
		}

		OrderMaster orderMaster = orderMasterOptional.get();
		if (orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
			throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
		}

		orderMaster.setOrderStatus(OrderStatusEnum.COMPLETED.getCode());
		orderMasterRepository.save(orderMaster);

		List<OrderDetails> orderDtlsList = orderDetailsRepository.findByOrderId(orderId);
		if (CollectionUtils.isEmpty(orderDtlsList)) {
			throw new OrderException(ResultEnum.ORDER_DETAILS_NOT_FOUND);
		}

		OrderDTO orderDto = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDto);
		orderDto.setOrderDetailsList(orderDtlsList);

		return orderDto;
	}

}
