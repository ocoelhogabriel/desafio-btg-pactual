package com.gctech.btgpactual.ordems.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "tb_orders")
public class OrderEntity {

	@MongoId
	private Long orderId;
	@Indexed(name = "customer_id_index")
	private Long costomerId;

	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal orderValue;

	private List<OrderItems> items;

	public OrderEntity() {
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCostomerId() {
		return costomerId;
	}

	public void setCostomerId(Long costomerId) {
		this.costomerId = costomerId;
	}

	public BigDecimal getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(BigDecimal orderValue) {
		this.orderValue = orderValue;
	}

	public List<OrderItems> getItems() {
		return items;
	}

	public void setItems(List<OrderItems> items) {
		this.items = items;
	}
}
