/**
 * 
 */
package com.omazan.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Banashri
 *
 */
public class ShipmentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
    int shipment_id;
    
    int order_id;
    
    int truck_id;
    
    private String exception_message;
    
    private String shipmentposition;
    
    private String status;
    
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getTruck_id() {
		return truck_id;
	}
	public void setTruck_id(int truck_id) {
		this.truck_id = truck_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getShipment_id() {
		return shipment_id;
	}
	public void setShipment_id(int shipment_id) {
		this.shipment_id = shipment_id;
	}
	public String getShipmentposition() {
		return shipmentposition;
	}
	public void setShipmentposition(String shipmentposition) {
		this.shipmentposition = shipmentposition;
	}
	public String getException_message() {
		return exception_message;
	}
	public void setException_message(String exception_message) {
		this.exception_message = exception_message;
	}
}
