package com.gctech.btgpactual.ordems.listener;

import com.gctech.btgpactual.ordems.listener.dto.OrderCreateEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.gctech.btgpactual.ordems.config.RabbitMqConfig.ORDER_CREATE_QUEUE;

@Component
public class OrderCreateListener {

	@RabbitListener(queues = ORDER_CREATE_QUEUE)
	public void listen(Message<OrderCreateEvent> messege){

	}

}
