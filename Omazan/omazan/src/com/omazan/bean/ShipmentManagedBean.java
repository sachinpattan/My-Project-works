/**
 * 
 */
package com.omazan.bean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.omazan.hbm.Order;
import com.omazan.hbm.Shipment;
import com.omazan.hbm.User;
import com.omazan.util.HibernateUtil;

/**
 * @author Banashri
 * 
 */
@SessionScoped
public class ShipmentManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	private Shipment editingShipment;

	private String status;
	private String shipmentPosition;

	private int shipmentId;
	private int orderId;

	private List<Shipment> nonDeliveredShipments;
	private List<Shipment> shipments;
	private Shipment shipmentForOrder;

	public List<Shipment> getShipments() {
		getShipmentDetailsByOrderId();
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Shipment getEditingShipment() {
		return editingShipment;
	}

	public void setEditingShipment(Shipment editingShipment) {
		this.editingShipment = editingShipment;
	}

	public List<Shipment> getNonDeliveredShipments() {
		getNonDelieveredShipments();
		return nonDeliveredShipments;
	}

	public void setNonDeliveredShipments(List<Shipment> nonDeliveredShipments) {
		this.nonDeliveredShipments = nonDeliveredShipments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShipmentPosition() {
		return shipmentPosition;
	}

	public void setShipmentPosition(String shipmentPosition) {
		this.shipmentPosition = shipmentPosition;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String loadShipment() {
		int shipmentId = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("shipmentId"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria shipmentCriteria = session.createCriteria(Shipment.class);
		shipmentCriteria.add(Restrictions.eq("shipmentId", shipmentId));
		this.editingShipment = (Shipment) shipmentCriteria.uniqueResult();

		return "editShipment";
	}

	public String updateShipment() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String status;
		Shipment shipment = this.editingShipment;

		System.out.println("Status obtained:" + shipment.getStatus());
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(shipment);

			if (shipment.getStatus().equalsIgnoreCase("Delievered")) {

				Query q = session.createQuery("from Order order");
				List<Order> orders = (List<Order>) q.list();

				Iterator it = orders.iterator();
				while (it.hasNext()) {
					Order order = (Order) it.next();
					if (order.getOrderId() == shipment.getOrderId()) {
						order.setStatus("Completed");
						session.saveOrUpdate(order);
					}
				}
			}

			tx.commit();
			status = SUCCESS;
			getNonDelieveredShipments();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			status = ERROR;
			e.printStackTrace();

		} finally {
			session.close();
		}
		return status;
	}

	public String getNonDelieveredShipments() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Query q = session
				.createQuery("from Shipment shipment where shipment.status !='Delievered'");
		List<Shipment> result = (List<Shipment>) q.list();

		this.setNonDeliveredShipments(result);

		System.out.println(result);
		session.close();
		return SUCCESS;

	}

	public String saveShipment() {
		String status = "";
		Session session = HibernateUtil.getSessionFactory().openSession();

		Shipment shipment = new Shipment();
		shipment.setShipmentPosition(getShipmentPosition());
		shipment.setStatus(getStatus());
		shipment.setOrderId(getOrderId());

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(shipment);
			tx.commit();
			status = SUCCESS;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			status = ERROR;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	public String getShipmentDetailsByOrderId() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session
				.createQuery("from Shipment shipment where shipment.orderId = :orderId");
		System.out.println(this.getOrderId());
		q.setParameter("orderId", this.getOrderId());
		List<Shipment> result = q.list();
		setShipments(result);
		session.close();
		return SUCCESS;
	}

	public String getShipmentDetailsForOrderId() {

		User user = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");
		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.flush();
		session.clear();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		int orderId = Integer.parseInt(params.get("orderId"));
//		session.clear();

		Query q = session
				.createQuery("from Shipment shipment where shipment.orderId ="
						+ orderId);
		Shipment result = (Shipment) q.uniqueResult();
		setShipmentForOrder(result);
		session.refresh(result);
		session.close();
		return SUCCESS;
	}

	public Shipment getShipmentForOrder() {
		getShipmentDetailsForOrderId();
		return shipmentForOrder;
	}

	public void setShipmentForOrder(Shipment shipmentForOrder) {
		this.shipmentForOrder = shipmentForOrder;
	}

}
