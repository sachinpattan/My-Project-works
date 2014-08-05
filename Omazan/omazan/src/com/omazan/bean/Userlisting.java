package com.omazan.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.omazan.hbm.Address;
import com.omazan.hbm.Order;
import com.omazan.hbm.Orderedproducts;
import com.omazan.hbm.Product;
import com.omazan.hbm.User;
import com.omazan.util.HibernateUtil;
import com.omazan.util.PasswordEncryption;

public class Userlisting {

	private int Uid = 0;

	public Userlisting() {
		this.Uid = Uid;
	}

	public void listUsers(int Uid) {

		User user = new User();
		Session session = HibernateUtil.getSessionFactory().openSession();

		System.out.println("Hello, inside user listing");
		Query query = session.createQuery("from User where User_id =" + Uid);

		List<User> testuser = (List<User>) query.list();
		if (testuser.isEmpty()) {
			System.out.println("no user found");
		} else {
			System.out.println("user found checking for details");
			User newUser = testuser.get(0);
			System.out.println("password" + newUser.getPassword());
			System.out.println("First Name:" + newUser.getFirstName());
			System.out.println("Last Name:" + newUser.getLastName());
			System.out.println("Mobile:" + newUser.getMobile());
			System.out.println("Email" + newUser.getEmail());
			System.out.println("User Type:" + newUser.getType());
		}
	}

	public void updateUser(int Uid) {
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		Scanner keyIn = new Scanner(System.in);
		User user = new User();
		Session session = HibernateUtil.getSessionFactory().openSession();
		loop: while (true) {
			System.out.println("Enter the field you want to edit");
			System.out.println("1. First Name");
			System.out.println("2. Last Name");
			System.out.println("3. Mobile");
			System.out.println("4. Email");
			System.out.println("5. Exit");
			int option = Integer.parseInt(keyIn.nextLine());
			switch (option) {
			case 1:
				System.out.println("Please enter the new value");
				String firstName = null;
				try {
					firstName = bufRead.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.setFirstName(firstName);
				String firstname = user.getFirstName();
				System.out.println("newly entered firstname is : "
						+ user.getFirstName());
				String sql1 = "update User set firstname = :firstName where User_id = :Uid";
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery(sql1);
				query.setString("firstName", firstname);
				query.setParameter("Uid", Uid);
				int result = query.executeUpdate();
				tx.commit();
				System.out.println("Number of rows effected" + result);
				break;
			case 2:
				System.out.println("Please enter the new value");
				String lastName = null;
				try {
					lastName = bufRead.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.setLastName(lastName);
				System.out.println("Newly entered last name is : " + lastName);
				String sql2 = "update User set lastname = :lastName where User_id = :Uid";
				Query query2 = session.createQuery(sql2);
				query2.setString("lastName", lastName);
				query2.setParameter("Uid", Uid);

				int result2 = query2.executeUpdate();

				System.out.println("Number of rows effected" + result2);
				break;
			case 3:
				System.out.println("Please enter the new value");
				int Mobile = Integer.parseInt(keyIn.nextLine());
				user.setMobile(Mobile);
				System.out
						.println("Newly entered Mobile number is : " + Mobile);
				String sql3 = "update User set mobile = :Mobile where User_id = :Uid";
				Query query3 = session.createQuery(sql3);
				query3.setParameter("Mobile", Mobile);
				query3.setParameter("Uid", Uid);
				int result3 = query3.executeUpdate();

				System.out.println("Number of rows effected" + result3);
				break;
			case 4:
				System.out.println("Please enter the new value");
				String Email = null;
				try {
					Email = bufRead.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.setEmail(Email);
				System.out.println("Newly entered Email is : " + Email);
				String sql4 = "update User set email = :email where User_id = :Uid";
				Query query4 = session.createQuery(sql4);
				query4.setString("Email", Email);
				query4.setParameter("Uid", Uid);
				int result4 = query4.executeUpdate();
				System.out.println("Number of rows effected" + result4);
				break;

			default:
				break loop;

			}
		}

	}

	public static void main(String[] args) {

		int id, option;
		Userlisting userDetails = new Userlisting();
		Scanner keyIn = new Scanner(System.in);
		while (true) {
			System.out
					.println("Welcome to the Userlisting, Please select from the below options");
			System.out.println("1. View the User Details");
			System.out.println("2. Edit the User Details");
			System.out.println("3. Exit");
			System.out.print("Enter");
			option = Integer.parseInt(keyIn.nextLine());
			switch (option) {
			case 1:
				System.out.println("please enter the UID of the user");
				id = Integer.parseInt(keyIn.nextLine());
				userDetails.listUsers(id);
				break;
			case 2:
				System.out.println("please enter the UID of the user");
				id = Integer.parseInt(keyIn.nextLine());
				Session session = HibernateUtil.getSessionFactory()
						.openSession();
				Query query = session.createQuery("from User where User_id ="
						+ id);
				List<User> testuser = (List<User>) query.list();
				if (testuser.isEmpty()) {
					System.out.println("no user found");
				} else {
					userDetails.updateUser(id);
				}
				break;
			default:
				System.out.println("Thank you for using the admin System");
				System.exit(0);
			}

		}

	}

}
