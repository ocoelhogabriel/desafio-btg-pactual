package com.gctech.btgpactual.ordems.entity;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigInteger;

public class OrderItems {

	private String product;
	private Integer quantity;
	@Field(targetType = FieldType.DECIMAL128)
	private BigInteger price;

	public OrderItems() {
		// TODO document why this constructor is empty
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}
}
