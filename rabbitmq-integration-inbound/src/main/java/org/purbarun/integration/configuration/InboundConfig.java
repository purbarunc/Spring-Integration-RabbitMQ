package org.purbarun.integration.configuration;

import org.purbarun.integration.model.OrderMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;

@Configuration
public class InboundConfig {
	private static final String QUEUE = "NEW_ORDER";

	@Bean
	IntegrationFlow rabbitInboundFlow(ConnectionFactory connectionFactory) {
		return IntegrationFlow.from(Amqp.inboundAdapter(connectionFactory, QUEUE))
				.transform(Transformers.fromJson(OrderMessage.class)).handle("orderService", "process").get();
	}
}
