package com.omazan.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.omazan.hbm.Address;
import com.omazan.hbm.Product;
import com.omazan.hbm.User;
import com.omazan.util.HibernateUtil;
import com.omazan.util.PasswordEncryption;

public class SaveAdmin implements Serializable {
	
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String type;
	private int mobile;

	private String message;
	private Address address = null;
	private List<Product> products;
	
	public void SaveAdmin() { }

      private String SaveAdminUser() {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		address = new Address();
		User user = new User();
		user.setTitle("Mr");
		user.setFirstName("admin");
		user.setLastName("one");
		user.setEmail("adminone@omazon.com");
		user.setPassword(PasswordEncryption.encrypt("AdminOne"));
		user.setType("B");
		user.setMobile(98765);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();		
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("user", user);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();

				// Message error = new MessageImpl(1,"Same Email Address",null);
			}


		

		} finally {
			session.close();
		}
		return result;
	}

public static void main (String[] args) {
	SaveAdmin admin = new SaveAdmin();
	admin.SaveAdminUser();
	
   }

}