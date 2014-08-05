/**
 * 
 */
package com.omazan.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.omazan.hbm.Product;
import com.omazan.util.HibernateUtil;

/**
 * @author Banashri
 * 
 */
@SessionScoped
public class ProductManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private Product editingProduct;
	String name, description, imageUrl;
	int quantity, price;
	private List<Product> products;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public String save() {
		return saveProduct();
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String loadProduct() {
		int productId = Integer
				.parseInt(FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap()
						.get("productId"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria productCriteria = session.createCriteria(Product.class);
		productCriteria.add(Restrictions.eq("productId", productId));
		this.editingProduct = (Product) productCriteria.uniqueResult();

		return "editProduct";
	}

	public String updateProduct() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String status;
		Product product = this.editingProduct;

		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(product);

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

	private String saveProduct() {
		String status = "";

		Session session = HibernateUtil.getSessionFactory().openSession();

		Product product = new Product();
		product.setName(this.getName());
		product.setDescription(this.getDescription());
		product.setImageURL(this.getImageUrl());
		product.setPrice(this.getPrice());
		product.setQuantity(this.getQuantity());
		product.setCategoryId("1");

		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(product);

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

	public Product getEditingProduct() {
		return editingProduct;
	}

	public void setEditingProduct(Product editingProduct) {
		this.editingProduct = editingProduct;
	}

	@SuppressWarnings("unchecked")
	private void getAllProducts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria productCriteria = session.createCriteria(Product.class);
		this.setProducts(productCriteria.list());
	}

	public List<Product> getProducts() {
		getAllProducts();
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
