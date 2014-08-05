// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Read_Write_Search_Rec.java

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Read_Write_Search_Rec {

	public Read_Write_Search_Rec() {
		emp = new ArrayList();
		products = new ArrayList();
		orders = new ArrayList();
		shipments = new ArrayList();
		connect();
	}

	public void writeRec(ArrayList r, String recordtype) throws Exception {
		FileOutputStream fout = new FileOutputStream((new StringBuilder(
				String.valueOf(recordtype))).append(".dat").toString());
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(r);
		oos.close();
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager
					.getConnection("jdbc:mysql://localhost/omazan?user=root&password=sachin");
			st = con.createStatement();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int readUser() {
		label0: {
			try {
				File f = new File("User.dat");
				if (!f.exists())
					break label0;
				FileInputStream fin = new FileInputStream("User.dat");
				ObjectInputStream ois = new ObjectInputStream(fin);
				emp = (ArrayList) ois.readObject();
				ois.close();
			} catch (Exception r) {
				return 0;
			}
			return 1;
		}
		return 0;
	}

	public int readProduct() {
		label0: {
			try {
				File f = new File("Product.dat");
				if (!f.exists())
					break label0;
				FileInputStream fin = new FileInputStream("Product.dat");
				ObjectInputStream ois = new ObjectInputStream(fin);
				products = (ArrayList) ois.readObject();
				ois.close();
			} catch (Exception r) {
				return 0;
			}
			return 1;
		}
		return 0;
	}

	public int readOrder() {
		label0: {
			try {
				File f = new File("Order.dat");
				if (!f.exists())
					break label0;
				FileInputStream fin = new FileInputStream("Order.dat");
				ObjectInputStream ois = new ObjectInputStream(fin);
				orders = (ArrayList) ois.readObject();
				ois.close();
			} catch (Exception r) {
				return 0;
			}
			return 1;
		}
		return 0;
	}

	public int readShipment() {
		label0: {
			try {
				File f = new File("Shipment.dat");
				if (!f.exists())
					break label0;
				FileInputStream fin = new FileInputStream("Shipment.dat");
				ObjectInputStream ois = new ObjectInputStream(fin);
				shipments = (ArrayList) ois.readObject();
				ois.close();
			} catch (Exception r) {
				return 0;
			}
			return 1;
		}
		return 0;
	}

	public void PrintUsers() {
		readUser();
		for (int i = 0; i < emp.size(); i++) {
			User t = (User) emp.get(i);
			printUser(t);
		}

	}

	private void printUser(User t) {
		System.out.println("---------------------------------------");
		System.out.println((new StringBuilder("User Id : ")).append(
				t.getUserId()).toString());
		System.out.println((new StringBuilder("FirstName : ")).append(
				t.getFirstName()).toString());
		System.out.println((new StringBuilder("Last Name : ")).append(
				t.getLastName()).toString());
		System.out.println((new StringBuilder("Email Id : ")).append(
				t.getEmail()).toString());
		System.out.println((new StringBuilder("Mobile No : ")).append(
				t.getMobile()).toString());
		System.out.println("---------------------------------------");
	}

	public void PrintProducts() {
		readProduct();
		for (int i = 0; i < products.size(); i++) {
			Product t = (Product) products.get(i);
			printProduct(t);
		}

	}

	private void printProduct(Product t) {
		System.out.println("---------------------------------------");
		System.out.println((new StringBuilder("Product Id : ")).append(
				t.getProductId()).toString());
		System.out.println((new StringBuilder("Product Name : ")).append(
				t.getName()).toString());
		System.out.println((new StringBuilder("Description : ")).append(
				t.getDescription()).toString());
		System.out.println((new StringBuilder("Quanity : ")).append(
				t.getQuantity()).toString());
		System.out.println((new StringBuilder("Price : ")).append(
				t.getPrice()).toString());
		System.out.println("---------------------------------------");
	}

	public void PrintOrders() {
		readOrder();
		for (int i = 0; i < orders.size(); i++) {
			Order t = (Order) orders.get(i);
			printOrder(t);
		}

	}

	private void printOrder(Order t) {
		System.out.println("---------------------------------------");
		System.out.println((new StringBuilder("OrderId : ")).append(
				t.getOrderId()).toString());
		System.out.println((new StringBuilder("UserId : ")).append(
				t.getUserId()).toString());
		System.out.println((new StringBuilder("Order Plcaed Time : ")).append(
				t.getOrderPlacedTime()).toString());
		System.out.println((new StringBuilder("Status : ")).append(
				t.getStatus()).toString());
		System.out.println("---------------------------------------");
	}

	public void PrintShipments() {
		readShipment();
		for (int i = 0; i < shipments.size(); i++) {
			Shipment t = (Shipment) shipments.get(i);
			printShipment(t);
		}

	}

	private void printShipment(Shipment t) {
		System.out.println("---------------------------------------");
		System.out.println((new StringBuilder("OrderId : ")).append(
				t.getOrderId()).toString());
		System.out.println((new StringBuilder("ShipmentId : ")).append(
				t.getShipmentId()).toString());
		System.out.println((new StringBuilder("Status: "))
				.append(t.getStatus()).toString());
		System.out.println((new StringBuilder("Shipment Position : ")).append(
				t.getShipmentPosition()).toString());
		System.out.println("---------------------------------------");
	}

	public void SearchEmp(int id) {
		readUser();
		for (int i = 0; i < emp.size(); i++) {
			User t = (User) emp.get(i);
			if (t.getUserId() != id)
				continue;
			printUser(t);
			break;
		}

	}

	public void SearchProduct(int id) {
		readProduct();
		for (int i = 0; i < products.size(); i++) {
			Product t = (Product) products.get(i);
			if (t.getProductId() != id)
				continue;
			printProduct(t);
			break;
		}

	}

	public void SearchOrder(int id) {
		readOrder();
		for (int i = 0; i < orders.size(); i++) {
			Order t = (Order) orders.get(i);
			if (t.getOrderId() != id)
				continue;
			printOrder(t);
			break;
		}

	}

	public void SearchShipment(int id) {
		readShipment();
		for (int i = 0; i < shipments.size(); i++) {
			Shipment t = (Shipment) shipments.get(i);
			if (t.getShipmentId() != id)
				continue;
			printShipment(t);
			break;
		}

	}

	public void loadDataFromServer() {
		System.out.println("Fetching Employees");
		loadUsers();
		System.out.println("Fetching Products");
		loadProducts();
		System.out.println("Fetching Orders");
		loadOrders();
		System.out.println("Fetching Shipments");
		loadShipments();
	}

	private void loadProducts() {
		String sql = "SELECT * from omazan.product p ";
		try {
			Product product;
			for (rs = st.executeQuery(sql); rs.next(); products.add(product)) {
				int productId = rs.getInt("product_id");
				String categoryId = rs.getString("CATEGORY_Id");
				String productName = rs.getString("NAME");
				String productDescription = rs.getString("DESCRIPTION");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				product = new Product();
				product.setProductId(productId);
				product.setCategoryId(categoryId);
				product.setDescription(productDescription);
				product.setName(productName);
				product.setQuantity(quantity);
				product.setPrice(price);
			}

			try {
				writeRec(products, "Product");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadUsers() {
		String sql = "SELECT * from omazan.user u ";
		try {
			User user;
			for (rs = st.executeQuery(sql); rs.next(); emp.add(user)) {
				int userId = rs.getInt("user_id");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				String email = rs.getString("EMAIL");
				String password = rs.getString("PASSWORD");
				int mobileno = rs.getInt("MOBILE");
				user = new User();
				user.setUserId(userId);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setMobile(mobileno);
				user.setEmail(email);
				user.setPassword(password);
			}

			try {
				writeRec(emp, "User");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadOrders() {
		String sql = "SELECT * from omazan.order o ";
		try {
			Order order;
			for (rs = st.executeQuery(sql); rs.next(); orders.add(order)) {
				int userId = rs.getInt("user_id");
				int orderId = rs.getInt("order_id");
				String status = rs.getString("STATUS");
				Date orderPlacedDateTime = rs.getDate("orderplacedtime");
				order = new Order();
				order.setUserId(userId);
				order.setOrderId(orderId);
				order.setOrderPlacedTime(new Timestamp(orderPlacedDateTime
						.getTime()));
				order.setStatus(status);
			}

			try {
				writeRec(orders, "Order");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadShipments() {
		String sql = "SELECT * from omazan.shipment s ";
		try {
			Shipment shipment;
			for (rs = st.executeQuery(sql); rs.next(); shipments.add(shipment)) {
				int shipmentId = rs.getInt("shipment_id");
				int orderId = rs.getInt("order_id");
				String status = rs.getString("STATUS");
				String shipmentPosition = rs.getString("SHIPMENTPOSITION");
				shipment = new Shipment();
				shipment.setShipmentId(shipmentId);
				shipment.setOrderId(orderId);
				shipment.setShipmentPosition(shipmentPosition);
				shipment.setStatus(status);
			}

			try {
				writeRec(shipments, "Shipment");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Read_Write_Search_Rec rws = new Read_Write_Search_Rec();
		rws.loadDataFromServer();
		boolean offline = false;
		do {
			Scanner keyIn = new Scanner(System.in);
			if (!offline) {
				System.out.println("Do you want to work offline? Y/N");
				if (keyIn.nextLine().equals("Y")) {
					offline = true;
				}
			} else {
				System.out.println("Do you want to work onlineline? Y/N");
				if (keyIn.nextLine().equals("Y")) {
					rws.loadDataFromServer();
					offline = false;
				}
			}

			System.out
					.println(" 1:Display all Users \n 2: Display all Products \n 3:Display all Orders \n 4: Display all Shipments ");
			System.out
					.println(" 5:Search for Users \n 6:Search for Products \n 7:Search for Orders \n 8:Search for Shipments ");
			System.out.println("Enter your Choice");
			int choice = Integer.parseInt(keyIn.nextLine());
			printSwitch(rws, choice);
		} while (true);
	}

	private static void printSwitch(Read_Write_Search_Rec rws, int choice) {
		Scanner keyIn = new Scanner(System.in);
		switch (choice) {
		case 1: // '\001'
		{
			rws.PrintUsers();
			break;
		}

		case 2: // '\002'
		{
			rws.PrintProducts();
			break;
		}

		case 3: // '\003'
		{
			rws.PrintOrders();
			break;
		}

		case 4: // '\004'
		{
			rws.PrintShipments();
			break;
		}

		case 5: // '\005'
		{
			System.out.println("Enter the user Id");
			int id = Integer.parseInt(keyIn.nextLine());
			rws.SearchEmp(id);
			break;
		}

		case 6: // '\006'
		{
			System.out.println("Enter the product Id");
			int id = Integer.parseInt(keyIn.nextLine());
			rws.SearchProduct(id);
			break;
		}

		case 7: // '\007'
		{
			System.out.println("Enter the Order Id");
			int id = Integer.parseInt(keyIn.nextLine());
			rws.SearchOrder(id);
			break;
		}

		case 8: // '\b'
		{
			System.out.println("Enter the Shipment Id");
			int id = Integer.parseInt(keyIn.nextLine());
			rws.SearchShipment(id);
			break;
		}
		}
	}

	ArrayList emp;
	ArrayList products;
	ArrayList orders;
	ArrayList shipments;
	Statement st;
	ResultSet rs;
	Connection con;
}
