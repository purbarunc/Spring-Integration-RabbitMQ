package org.purbarun.integration.service;

import org.purbarun.integration.model.OrderMessage;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("orderService")
@Slf4j
public class OrderService {
	public void process(OrderMessage orderMessage) {
		log.info("Order Received from RabbitMQ => {}", orderMessage);
	}
}
