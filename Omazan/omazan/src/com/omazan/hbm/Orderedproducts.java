package com.omazan.hbm;

import javax.persistence.GeneratedValue;

/**
 * @author Sachin Pattan
 * @since 30 Oct 2013
 * @version 1.0.0
 *
 */
public class Orderedproducts {
	
	private int orderId;
	private int productId;
	private int quantity;
	private int orderedproductsId;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOrderedproductsId() {
		return orderedproductsId;
	}
	public void setOrderedproductsId(int orderedproductsId) {
		this.orderedproductsId = orderedproductsId;
	}

}
