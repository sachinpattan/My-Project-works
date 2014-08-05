/**
 * 
 */
package com.omazan.mobile.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.InitialContext;

import com.omazan.bean.OrderEntity;
import com.omazan.bean.ProductEntity;
import com.omazan.bean.ShipmentEntity;
import com.omazan.bean.UserEntity;
import com.omazan.mobile.Coordinator;
import com.omazan.mobile.common.MobileUtility;

/**
 * @author Banashri
 *
 */
public class MobileClient {

	Coordinator coordinator;
	
	
	List users;
	List products;
	List orders;
	List shipments;
	
	
	public List getUsers() {
		return users;
	}

	public void setUsers(List users) {
		this.users = users;
	}

	public List getProducts() {
		return products;
	}

	public void setProducts(List products) {
		this.products = products;
	}

	public List getOrders() {
		return orders;
	}

	public void setOrders(List orders) {
		this.orders = orders;
	}

	public List getShipments() {
		return shipments;
	}

	public void setShipments(List shipments) {
		this.shipments = shipments;
	}

	public MobileClient() {
		users = new ArrayList();
		products = new ArrayList();
		orders = new ArrayList();
		shipments = new ArrayList();
	}
	public static void main(String[] args) {
		
		MobileClient client = new MobileClient();

		//Connect to server
		
		try {
			InitialContext cntxt = new InitialContext();
			cntxt = new InitialContext(MobileUtility.loadProperties("localhost", "3700"));
			Coordinator coordinator = (Coordinator) cntxt.lookup("Coordinator");
			client.coordinator = coordinator;
			
			MobileUtility.saveDataToLocal(client.coordinator.loadData());
			
			boolean offline = false;
			do {
				Scanner keyIn = new Scanner(System.in);
				if (!offline) {
					System.out.println("Do you want to work offline? Y/N");
					if (keyIn.nextLine().equals("Y")) {
						offline = true;
					}
				} else {
					System.out.println("Do you want to work online? Y/N");
					if (keyIn.nextLine().equals("Y")) {
						MobileUtility.saveDataToLocal(client.coordinator.loadData());
						offline = false;
					}
				}

				System.out
						.println(" 1:Display all Users \n 2: Display all Products \n 3:Display all Orders \n 4: Display all Shipments ");
				System.out
						.println(" 5:Search for Users \n 6:Search for Products \n 7:Search for Orders \n 8:Search for Shipments ");
				System.out.println("Enter your Choice");
				int choice = Integer.parseInt(keyIn.nextLine());
				printSwitch(client, choice);
			} while (true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void printSwitch(MobileClient client, int choice) {
		Scanner keyIn = new Scanner(System.in);
		switch (choice) {
		case 1: // '\001'
		{
			printUsers(client);
			break;
		}

		case 2: // '\002'
		{
			printProducts(client);
			break;
		}

		case 3: // '\003'
		{
			printOrders(client);
			break;
		}

		case 4: // '\004'
		{
			printShipments(client);
			break;
		}

		case 5: // '\005'
		{
			System.out.println("Enter the user Id");
			int id = Integer.parseInt(keyIn.nextLine());
			searchUser(client, id);
			break;
		}

		case 6: // '\006'
		{
			System.out.println("Enter the product Id");
			int id = Integer.parseInt(keyIn.nextLine());
			searchProduct(client, id);
			break;
		}

		case 7: // '\007'
		{
			System.out.println("Enter the Order Id");
			int id = Integer.parseInt(keyIn.nextLine());
			searchOrder(client, id);
			break;
		}

		case 8: // '\b'
		{
			System.out.println("Enter the Shipment Id");
			int id = Integer.parseInt(keyIn.nextLine());
			searchShipment(client, id);
			break;
		}
		}
	}

	private static void searchUser(MobileClient client, int id) {
		MobileUtility.readUserFile(client);
		for (int i = 0; i < client.getUsers().size(); i++) {
			UserEntity t = (UserEntity) client.getUsers().get(i);
			if (t.getUser_id() != id)
				continue;
			MobileUtility.printUser(t);
			break;
		}
	}
	
	private static void searchProduct(MobileClient client, int id) {
		MobileUtility.readProductFile(client);
		for (int i = 0; i < client.getProducts().size(); i++) {
			ProductEntity t = (ProductEntity) client.getProducts().get(i);
			if (t.getProduct_id() != id)
				continue;
			MobileUtility.printProduct(t);
			break;
		}
	}
	
	private static void searchOrder(MobileClient client, int id) {
		MobileUtility.readOrderFile(client);
		for (int i = 0; i < client.getOrders().size(); i++) {
			OrderEntity t = (OrderEntity) client.getOrders().get(i);
			if (t.getOrder_id() != id)
				continue;
			MobileUtility.printOrder(t);
			break;
		}
	}

	private static void searchShipment(MobileClient client, int id) {
		MobileUtility.readShipmentFile(client);
		for (int i = 0; i < client.getShipments().size(); i++) {
			ShipmentEntity t = (ShipmentEntity) client.getShipments().get(i);
			if (t.getShipment_id() != id)
				continue;
			MobileUtility.printShipment(t);
			break;
		}
	}
	
	private static void printProducts(MobileClient client) {
		MobileUtility.readProductFile(client);
		for (int i = 0; i < client.getProducts().size(); i++) {
			ProductEntity t = (ProductEntity) client.getProducts().get(i);
			MobileUtility.printProduct(t);
		}
	}
	
	private static void printOrders(MobileClient client) {
		MobileUtility.readOrderFile(client);
		for (int i = 0; i < client.getOrders().size(); i++) {
			OrderEntity t = (OrderEntity) client.getOrders().get(i);
			MobileUtility.printOrder(t);
		}
	}
	
	private static void printShipments(MobileClient client) {
		MobileUtility.readShipmentFile(client);
		for (int i = 0; i < client.getShipments().size(); i++) {
			ShipmentEntity t = (ShipmentEntity) client.getShipments().get(i);
			MobileUtility.printShipment(t);
		}
	}
	private static void printUsers(MobileClient client) {
		MobileUtility.readUserFile(client);
		for (int i = 0; i < client.getUsers().size(); i++) {
			UserEntity t = (UserEntity) client.getUsers().get(i);
			MobileUtility.printUser(t);
		}
	}
}
