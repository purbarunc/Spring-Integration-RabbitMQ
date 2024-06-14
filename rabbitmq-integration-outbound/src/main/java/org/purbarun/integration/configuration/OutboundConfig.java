package org.purbarun.integration.configuration;

import org.purbarun.integration.utils.RabbitMQProperties;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.MessageChannel;

@Configuration
public class OutboundConfig {
	@Bean
	MessageChannel orderRequest() {
		return new DirectChannel();
	}

	@Bean
	IntegrationFlow rabbitOutboundFlow(AmqpTemplate template, RabbitMQProperties rabbitMQPropertiess) {
		return IntegrationFlow.from(orderRequest()).transform(Transformers.toJson())
				.handle(Amqp.outboundAdapter(template).exchangeName(rabbitMQPropertiess.getExchange())
						.routingKey(rabbitMQPropertiess.getRoutingKey()))
				.get();
	}
}
