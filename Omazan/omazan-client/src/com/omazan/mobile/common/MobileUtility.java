/**
 * 
 */
package com.omazan.mobile.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.omazan.bean.OrderEntity;
import com.omazan.bean.ProductEntity;
import com.omazan.bean.ShipmentEntity;
import com.omazan.bean.UserEntity;
import com.omazan.mobile.client.MobileClient;

/**
 * @author Banashri
 *
 */
public class MobileUtility {
	
	
	public static Properties loadProperties(String hostName, String portNo) {
		Properties props = new Properties();
		 
        props.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs",
                "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state",
                "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.CORBA.ORBInitialHost", hostName);
        props.setProperty("org.omg.CORBA.ORBInitialPort", portNo);
        
        return props;
	}
	
	public static void writeRecords(List r, String recordtype) throws Exception {
		FileOutputStream fout = new FileOutputStream((new StringBuilder(
				String.valueOf(recordtype))).append(".dat").toString());
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(r);
		oos.close();
		//System.out.println("Writing is done for "+recordtype);
	}
	
	public static void saveDataToLocal(Map dataMap) throws Exception {
		//System.out.println("Saving Users");
		writeRecords((List)dataMap.get("user"),"User");
		//System.out.println("Saving Products");
		writeRecords((List)dataMap.get("product"),"Product");
		//System.out.println("Saving Orders");
		writeRecords((List)dataMap.get("order"),"Order");
		//System.out.println("Saving Shipments");
		writeRecords((List)dataMap.get("shipment"),"Shipment");
	}
	
	public static int readUserFile(MobileClient client) {
		label0: {
			try {
				File f = new File("User.dat");
				if (!f.exists())
					break label0;
				FileInputStream fin = new FileInputStream("User.dat");
				ObjectInputStream ois = new ObjectInputStream(fin);
				client.setUsers((List) ois.readObject());
				
				//System.out.println("Hello .............."+client.getUsers());
				ois.close();
			} catch (Exception r) {
				return 0;
			}
			return 1;
		}
		return 0;
	}

	public static int readProductFile(MobileClient client) {
		label0: {
			try {
				File f = new File("Product.dat");
				if (!f.exists())
					break label0;
				FileInputStream fin = new FileInputStream("Product.dat");
				ObjectInputStream ois = new ObjectInputStream(fin);
				client.setProducts((List) ois.readObject());
				ois.close();
			} catch (Exception r) {
				return 0;
			}
			return 1;
		}
		return 0;
	}

	public static int readOrderFile(MobileClient client) {
		label0: {
			try {
				File f = new File("Order.dat");
				if (!f.exists())
					break label0;
				FileInputStream fin = new FileInputStream("Order.dat");
				ObjectInputStream ois = new ObjectInputStream(fin);
				client.setOrders((List) ois.readObject());
				ois.close();
			} catch (Exception r) {
				return 0;
			}
			return 1;
		}
		return 0;
	}

	public static int readShipmentFile(MobileClient client) {
		label0: {
			try {
				File f = new File("Shipment.dat");
				if (!f.exists())
					break label0;
				FileInputStream fin = new FileInputStream("Shipment.dat");
				ObjectInputStream ois = new ObjectInputStream(fin);
				client.setShipments((List) ois.readObject());
				ois.close();
			} catch (Exception r) {
				return 0;
			}
			return 1;
		}
		return 0;
	}
	
	
	
	
	
	
	public static void printUser(UserEntity t) {
		System.out.println("---------------------------------------");
		System.out.println((new StringBuilder("User Id : ")).append(
				t.getUser_id()).toString());
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
	
	public static void printProduct(ProductEntity t) {
		System.out.println("---------------------------------------");
		System.out.println((new StringBuilder("Product Id : ")).append(
				t.getProduct_id()).toString());
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
	
	public static void printOrder(OrderEntity t) {
		System.out.println("---------------------------------------");
		System.out.println((new StringBuilder("OrderId : ")).append(
				t.getOrder_id()).toString());
		System.out.println((new StringBuilder("UserId : ")).append(
				t.getUser_id()).toString());
		System.out.println((new StringBuilder("Order Plcaed Time : ")).append(
				t.getOrderPlacedTime()).toString());
		System.out.println((new StringBuilder("Status : ")).append(
				t.getStatus()).toString());
		System.out.println("---------------------------------------");
	}
	
	public static void printShipment(ShipmentEntity t) {
		System.out.println("---------------------------------------");
		System.out.println((new StringBuilder("OrderId : ")).append(
				t.getOrder_id()).toString());
		System.out.println((new StringBuilder("ShipmentId : ")).append(
				t.getShipment_id()).toString());
		System.out.println((new StringBuilder("Status: "))
				.append(t.getStatus()).toString());
		System.out.println((new StringBuilder("Shipment Position : ")).append(
				t.getShipmentposition()).toString());
		System.out.println("---------------------------------------");
	}
}
