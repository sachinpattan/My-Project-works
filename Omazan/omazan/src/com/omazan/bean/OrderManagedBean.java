/**
 * 
 */
package com.omazan.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
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
public class OrderManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String SUCCESS_ORDER_UPDATE = "successOrderUpdate";
	private static final String ERROR = "error";

	private List<Order> ordersList = new ArrayList<Order>();
	private List<Order> ordersListOfUser;
	private Order editingOrder;

	/**
	 * @return the ordersList
	 */
	public List<Order> getOrdersList() {

		return ordersList;
	}

	/**
	 * @param ordersList
	 *            the ordersList to set
	 */
	public void setOrdersList(List<Order> ordersList) {
		this.ordersList = ordersList;
	}

	public String getAllOrders() {

		System.out.println("Called: getAllOrders()");
		Session session = HibernateUtil.getSessionFactory().openSession();

		Query q = session
				.createQuery("from Order order where order.status='pending'");
		List<Order> result = (List<Order>) q.list();

		setOrdersList(result);
		session.close();
		System.out.println(result);
		return SUCCESS;
	}

	public List getOrdersOfUser(String userId) {

		return ordersList;
	}

	public List getOrdersWithStatus(String status) {
		return ordersList;
	}

	public List<Order> getOrdersListOfUser() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");
		Query q = session.createQuery("from Order order where order.userId="
				+ user.getUserId());
		this.ordersListOfUser = (List<Order>) q.list();

		session.close();
		return ordersListOfUser;
	}

	public void setOrdersListOfUser(List<Order> ordersListOfUser) {
		this.ordersListOfUser = ordersListOfUser;
	}

	public String loadOrder() {
		int orderId = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("orderId"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria shipmentCriteria = session.createCriteria(Order.class);
		shipmentCriteria.add(Restrictions.eq("orderId", orderId));
		this.editingOrder = (Order) shipmentCriteria.uniqueResult();
		session.close();
		return "editOrder";

	}

	public String updateOrder() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String status;
		Order order = this.editingOrder;

		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(order);

			tx.commit();
			status = SUCCESS_ORDER_UPDATE;
			getAllOrders();
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

	public Order getEditingOrder() {
		return editingOrder;
	}

	public void setEditingOrder(Order editingOrder) {
		this.editingOrder = editingOrder;
	}
}
