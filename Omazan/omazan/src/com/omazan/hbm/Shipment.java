package com.omazan.hbm;

import java.io.Serializable;

public class Shipment implements Serializable {
	private int shipmentId;
	private int orderId;
	private int truckId;
	private String shipmentPosition; 
	private String status;
	private String exceptionMessage; 
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getShipmentPosition() {
		return shipmentPosition;
	}
	public void setShipmentPosition(String shipmentPosition) {
		this.shipmentPosition = shipmentPosition;
	}
	public int getTruckId() {
		return truckId;
	}
	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	
}
