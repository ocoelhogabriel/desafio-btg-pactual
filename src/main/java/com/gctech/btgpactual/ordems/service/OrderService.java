package com.gctech.btgpactual.ordems.service;

import com.gctech.btgpactual.ordems.controller.dto.OrderResponse;
import com.gctech.btgpactual.ordems.entity.OrderEntity;
import com.gctech.btgpactual.ordems.entity.OrderItems;
import com.gctech.btgpactual.ordems.listener.dto.OrderCreateEvent;
import com.gctech.btgpactual.ordems.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void save(OrderCreateEvent event) {
        var entity = new OrderEntity();

        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());

        entity.setItems(getOrderItemsList(event));
        entity.setOrderValue(getTotalValueItems(event));
        logger.info("Entity OrderCreateEvent:  {}", entity);
        var save = orderRepository.save(entity);
        logger.info("Save Entity OrderCreateEvent:  {}", save);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageable) {
        var entity = orderRepository.findAllByCustomerId(customerId, pageable);
        logger.info("Get Entity OrderCreateEvent:  {}", entity.getContent());
        return entity.map(OrderResponse::fromEntity);
    }

    public BigDecimal findTotalOnOrderByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("orderValue").as("orderValue")
        );

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);
        return new BigDecimal(Objects.requireNonNull(response.getUniqueMappedResult(), String.format("Valor do total gasto do Cliente %d Nulo", customerId)).get("orderValue").toString());
    }

    private BigDecimal getTotalValueItems(OrderCreateEvent event) {
        return event.itens()
                .stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static List<OrderItems> getOrderItemsList(OrderCreateEvent event) {
        return event.itens().stream().map(i -> new OrderItems(i.produto(), i.quantidade(), i.preco())).toList();
    }

}
