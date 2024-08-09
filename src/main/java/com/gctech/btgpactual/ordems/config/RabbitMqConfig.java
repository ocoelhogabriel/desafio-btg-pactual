package com.gctech.btgpactual.ordems.config;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	public static final String ORDER_CREATE_QUEUE = "btg-pactual-order_create_queue";

	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Declarable orderCreateQueue() {
		return new Queue(ORDER_CREATE_QUEUE);
	}
}
