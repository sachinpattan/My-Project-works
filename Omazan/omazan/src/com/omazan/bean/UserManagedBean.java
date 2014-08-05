package com.omazan.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.omazan.hbm.Address;
import com.omazan.hbm.Order;
import com.omazan.hbm.Orderedproducts;
import com.omazan.hbm.Product;
import com.omazan.hbm.Shipment;
import com.omazan.hbm.User;
import com.omazan.util.HibernateUtil;
import com.omazan.util.PasswordEncryption;

/**
 * @author Sachin Pattan
 * @since 30 Oct 2013
 * @version 1.0.0
 * 
 */
@SessionScoped
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String type;
	private int mobile;
	private static final String CUSTOMER = "customer";
	private static final String ADMIN = "admin";
	private String message;
	private Address address = null;
	private List<Product> products;
	List<User> userList;
	private User editingUser;
	String flow = "user";

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Product> getProducts() {
		getAllProducts();
		return products;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String save() {
		return saveUsers();
	}

	@SuppressWarnings("unused")
	public String updateUsers() {

		String result = null;
		User user = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");
		result = update(user);
		return result;
	}

	@SuppressWarnings("unused")
	public String updateEditingUsers() {

		String result = null;
		result = update(this.editingUser);
		getAllUsers();
		return result;
	}

	private String update(User user) {
		String result;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(user);
			tx.commit();
			result = SUCCESS;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();

				// Message error = new MessageImpl(1,"Same Email Address",null);
			}

			result = ERROR;
			e.printStackTrace();

		} finally {
			session.close();
		}
		return result;
	}

	private String saveUsers() {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		address = new Address();
		User user = new User();
		user.setTitle(this.title);
		user.setFirstName(this.getFirstName());
		user.setLastName(this.getLastName());
		user.setEmail(this.getEmail());
		user.setPassword(PasswordEncryption.encrypt(this.getPassword()));
		user.setType("C");
		user.setMobile(this.getMobile());
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			if (flow.equals("admin")) {
				result = "adminsuccess";
			} else {
				result = SUCCESS;
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().put("user", user);
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();

				// Message error = new MessageImpl(1,"Same Email Address",null);
			}

			result = ERROR;
			e.printStackTrace();

		} finally {
			session.close();
		}
		return result;
	}

	public List<User> getUserList() {
		return userList;
	}

	public String getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<User> userList = session.createCriteria(User.class).list();

		setUserList(userList);
		session.close();

		return SUCCESS;
	}

	public String reset() {
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	@SuppressWarnings("unchecked")
	public String getAllProducts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria productCriteria = session.createCriteria(Product.class);
		products = productCriteria.list();
		this.setProducts(products);
		session.close();
		return SUCCESS;

	}

	/*
	 * public List<Product> getProductsByCategory(String categoryId) { Session
	 * session = HibernateUtil.getSessionFactory().openSession(); Criteria
	 * productCriteria = session.createCriteria(Product.class); products =
	 * productCriteria.list(); Iterator iterator = products.iterator(); while
	 * (iterator.hasNext()) { Product p = (Product) iterator.next(); if
	 * (!p.getCategoryId().equals(categoryId)) products.remove(p); } return
	 * products; }
	 */
	public void setProducts(List<Product> products) {

		this.products = products;
	}

	public String placeOrder() {
		String result = null;
		User currentUser = (User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("user");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		Shipment shipment = new Shipment();
		Order order = new Order();
		Address address = getAddress();
		address.setAddressType("Shipping");
		address.setUserId(currentUser.getUserId());
		order.setUserId(currentUser.getUserId());
		order.setStatus("Pending");
		order.setAddressId(address.getAddressId());
		order.setOrderPlacedTime(new Timestamp(new Date().getTime()));
		Transaction tx = session.beginTransaction();
		session.save(address);
		order.setAddressId(address.getAddressId());
		// order.setShipmentId(shipment.getShipmentId());
		session.save(order);
		shipment.setOrderId(order.getOrderId());
		shipment.setShipmentPosition("0x0");
		shipment.setStatus("Not Initiated");
		Random random = new Random();
		int truckId = (random.nextInt()) % 3;
		shipment.setTruckId(truckId + 1);
		session.save(shipment);
		// List<Orderedproducts> orderedProductsList = new
		// ArrayList<Orderedproducts>();
		for (Product product : this.products) {
			if (product.isProductOrdered()) {

				Orderedproducts orderedProducts = new Orderedproducts();
				orderedProducts.setOrderId(order.getOrderId());
				orderedProducts.setProductId(product.getProductId());
				session.save(orderedProducts);
			}
		}

		try {
			tx.commit();
			result = SUCCESS;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				result = ERROR;
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return result;

	}

	public Address getAddress() {

		if (address == null) {
			Session session = HibernateUtil.getSessionFactory().openSession();

			address = new Address();
			session.close();
		}
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getOrderDetails() {

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		int productId = Integer.parseInt(params.get("productId"));

		return "Success";

	}

	public String login() {
		return this.validateUser();
	}

	@SuppressWarnings("unused")
	public String validateUser() {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		System.out.println("password from page" + user.getPassword());
		Session session = HibernateUtil.getSessionFactory().openSession();

		System.out.println("Hello, Error Message!");
		Query query = session.createQuery("from User where email = :email");
		query.setParameter("email", email);

		@SuppressWarnings("unchecked")
		List<User> testuser = (List<User>) query.list();
		if (testuser.isEmpty()) {
			System.out.println("no user found");
			return ERROR;
		} else {
			System.out.println("user found checking for password");
			User newUser = testuser.get(0);
			System.out.println("password from DB" + newUser.getPassword());
			System.out.println("password from Web"
					+ PasswordEncryption.encrypt(user.getPassword()));
			if (newUser.getPassword().equals(
					PasswordEncryption.encrypt(user.getPassword()))) {
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().put("user", newUser);
				System.out.println("type" + newUser.getType());
				if (newUser.getType().matches("C")) {
					return CUSTOMER;
				} else {
					flow = "admin";
					return ADMIN;
				}

			} else {
				System.out.println("Passwords did not match");
				// return "ERROR";
			}

		}

		session.close();
		return ERROR;
	}

	public String loadUser() {
		int userId = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("userId"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria productCriteria = session.createCriteria(User.class);
		productCriteria.add(Restrictions.eq("userId", userId));
		this.editingUser = (User) productCriteria.uniqueResult();

		return "editUser";
	}

	public User getEditingUser() {
		return editingUser;
	}

	public void setEditingUser(User editingUser) {
		this.editingUser = editingUser;
	}
	
	 public String logout(){
	      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	      return "logout";
	   }
}