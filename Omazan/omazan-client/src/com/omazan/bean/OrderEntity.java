/**
 * 
 */
package com.omazan.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Banashri
 *
 */

public class OrderEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
    int order_id;
    
    int user_id;
    
    String status;
    
    int addressId;
    
    double orderTotal;
    
    Timestamp orderPlacedTime;
    
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Timestamp getOrderPlacedTime() {
		return orderPlacedTime;
	}

	public void setOrderPlacedTime(Timestamp orderPlacedTime) {
		this.orderPlacedTime = orderPlacedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}

