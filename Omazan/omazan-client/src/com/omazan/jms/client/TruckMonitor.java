package com.omazan.jms.client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


//http://dev.mysql.com/doc/refman/5.0/en/connector-j-usagenotes-glassfish-config.html
//http://stackoverflow.com/questions/16922729/persistence-exception-on-database-connection-using-glassfish
/**
 * @author Banashri
 *
 */
public class TruckMonitor extends JFrame {
	
	static JFrame frame;
	public static Session session;
	public static MessageProducer mp;
	public static InitialContext cntxt;
	
	public static void main(String args[]) {
		try {
    	  
    	// Provide the details of remote JMS Client
		  Properties props = new Properties();
		  props.put(Context.PROVIDER_URL, "mq://localhost:7676");
		  
		// Create the initial context for remote JMS server
		  cntxt = new InitialContext(props);
		  System.out.println("Context Created");
		   
		  // JNDI Lookup for QueueConnectionFactory in remote JMS Provider
		  QueueConnectionFactory qFactory = (QueueConnectionFactory)cntxt.lookup("OmazanQueueConnectionFactory");
		   
		  // Create a Connection from QueueConnectionFactory
		  Connection connection = qFactory.createConnection();
		  System.out.println("Connection established with JMS Provide ");
		  connection.start();
		  
		  // Initialise the communication session 
		  session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		
		  // JNDI Lookup for the Queue in remote JMS Provider
		  Queue queue = (Queue)cntxt.lookup("OmazanQueue");
		   
		  // Create the MessageProducer for this communication 
		  // Session on the Queue we have
		  mp = session.createProducer(queue);

		  createAndShowGUI();
		  
	  } catch (Exception ex) {
		  ex.printStackTrace();
	  }
	  
	}
	
	private static void createAndShowGUI() {
    	
		System.out.println("STARTED: createAndShowUI");
    	
    	frame = new JFrame("Reactive ERP - Tracking & Notification Tool");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// Make sure all the resources are released 
				try {
					mp.close();
					session.close();
					cntxt.close();
				} catch (JMSException | NamingException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        frame.setLayout(new FlowLayout());
        addComponentsToPane(frame.getContentPane());
        frame.pack();

        Insets insets = frame.getInsets();
        frame.setSize(new Dimension(insets.left + insets.right + 1800,
                insets.top + insets.bottom + 600));
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println("ENDED: createAndShowUI");
    }
	
	static JPanel panel;
	static JButton dButton, eButton, pButton;
	static JTextField shipmentId, pTruckNo, eTruckNo, lattitude, longitude, eMessage;

	private static void addComponentsToPane(Container contentPane) {
		
		System.out.println("STARTED: addComponentsToPane");
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,7));
		
		shipmentId = new JTextField(15);
		pTruckNo = new JTextField(15);
		eTruckNo = new JTextField(15);
		
		lattitude = new JTextField(15);
		longitude = new JTextField(15);
		eMessage = new JTextField(15);
		
		dButton = new JButton("Set as Delivered");
		eButton = new JButton("Set Exception");
		pButton = new JButton("Set Position");
		
		panel.add(new JLabel("Shipment Id"));
		panel.add(shipmentId);
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(dButton);
		
		dButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Create the message
				try {
					StringBuffer sbDeliveryEvent = new StringBuffer("<deliveryevent>");
					sbDeliveryEvent.append("<shipmentId>").append(shipmentId.getText()).append("</shipmentId>").append("</deliveryevent>");
					
					TextMessage message = session.createTextMessage();
					message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
					message.setText(sbDeliveryEvent.toString());
	
					// Send the message to Queue
					mp.send(message);

					session.commit(); 
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(new JLabel("Truck No"));
		panel.add(pTruckNo);
		panel.add(new JLabel("Lattitude"));
		panel.add(lattitude);
		panel.add(new JLabel("Longitude"));
		panel.add(longitude);
		panel.add(pButton);
		
		pButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Create the message
				try {
					StringBuffer sbPositionEvent = new StringBuffer("<positionEvent>");
					sbPositionEvent.append("<truckId>").append(pTruckNo.getText()).append("</truckId>");
					sbPositionEvent.append("<long>").append(longitude.getText()).append("</long>");
					sbPositionEvent.append("<lat>").append(lattitude.getText()).append("</lat>"); 
					sbPositionEvent.append("</positionEvent>");
					
					TextMessage message = session.createTextMessage();
					message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
					message.setText(sbPositionEvent.toString());
	
					// Send the message to Queue
					mp.send(message);

					session.commit(); 
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(new JLabel("Truck No"));
		panel.add(eTruckNo);
		panel.add(new JLabel("Exception Message"));
		panel.add(eMessage);
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(eButton);
		
		
		eButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Create the message
				try {
					StringBuffer sbExceptionEvent = new StringBuffer("<exceptionEvent>");
					sbExceptionEvent.append("<truckId>").append(eTruckNo.getText()).append("</truckId>");
					sbExceptionEvent.append("<exceptionDescription>").append(eMessage.getText()).append("</exceptionDescription>");
					sbExceptionEvent.append("</exceptionEvent>");
					
					TextMessage message = session.createTextMessage();
					message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
					message.setText(sbExceptionEvent.toString());
	
					// Send the message to Queue
					mp.send(message);

					session.commit(); 
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		contentPane.add(panel);
		System.out.println("ENDED: addComponentsToPane");
	}
}
