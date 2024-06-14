package org.purbarun.integration.controller;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.purbarun.integration.model.OrderMessage;
import org.purbarun.integration.model.OrderRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	private MessageChannel messageChannel;

	public OrderController(@Qualifier("orderRequest") MessageChannel messageChannel) {
		this.messageChannel = messageChannel;
	}

	@PostMapping("/order")
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		String msgId = UUID.randomUUID().toString();
		OrderMessage orderMessage = new OrderMessage(orderRequest, msgId);
		Message<OrderMessage> message = MessageBuilder.withPayload(orderMessage)
				.setHeader("dateTime", OffsetDateTime.now()).build();
		messageChannel.send(message);
		log.info("Order Published to RabbitMQ => {}", orderMessage);
		String responseMeassge = "Order Received with Message Id:" + msgId;
		return new ResponseEntity<>(responseMeassge, HttpStatus.OK);
	}
}
