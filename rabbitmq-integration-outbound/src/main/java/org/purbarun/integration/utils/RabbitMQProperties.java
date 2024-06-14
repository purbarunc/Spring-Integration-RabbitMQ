package org.purbarun.integration.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProperties {
	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.routingKey}")
	private String routingKey;
	
	public String getExchange() {
		return exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}
}
