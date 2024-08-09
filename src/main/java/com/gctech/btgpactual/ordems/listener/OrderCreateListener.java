package com.gctech.btgpactual.ordems.listener;

import com.gctech.btgpactual.ordems.listener.dto.OrderCreateEvent;
import com.gctech.btgpactual.ordems.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.gctech.btgpactual.ordems.config.RabbitMqConfig.ORDER_CREATE_QUEUE;

@Component
public class OrderCreateListener {

	private final Logger logger = LoggerFactory.getLogger(OrderCreateListener.class);

	private final OrderService orderService;

	public OrderCreateListener(OrderService orderService) {
		this.orderService = orderService;
	}

	@RabbitListener(queues = ORDER_CREATE_QUEUE)
	public void listen(Message<OrderCreateEvent> message) {
		logger.info("Message consumed: {}", message);

		orderService.save(message.getPayload());
	}

}
