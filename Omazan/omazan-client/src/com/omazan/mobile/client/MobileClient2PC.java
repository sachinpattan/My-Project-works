/**
 * 
 */
package com.omazan.mobile.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.StringUtils;
import com.omazan.mobile.Coordinator;
import com.omazan.mobile.common.MobileUtility;

/**
 * @author Banashri
 *
 */
public class MobileClient2PC {
	
	final static Logger logger = Logger.getLogger(MobileClient2PC.class.getName());
	
	private int clientID;
	Coordinator coordinator;
	Map loadedDataFromServer = new HashMap();
	
	public MobileClient2PC() {
		clientID = new Random().nextInt(30000);
	}

	public static void main(String[] args) {
		
		MobileClient2PC client = new MobileClient2PC();
		
		try {
			
			InitialContext cntxt = new InitialContext();
			cntxt = new InitialContext(MobileUtility.loadProperties("localhost", "3700"));
			Coordinator coordinator = (Coordinator) cntxt.lookup("Coordinator");
			
			coordinator.connect(client.clientID);
			
			client.coordinator = coordinator;
			
			if (!client.coordinator.isDataLoadInitiated()) {
				System.out.println("Would you like to update local database? (Y/N)");
				Scanner keyIn = new Scanner(System.in);

				if (keyIn.nextLine().equals("Y")) {
					coordinator.initDataLoad(client.clientID, true);
				}
			}

			doPolling(client);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * POLL State : ABORT(A) or COMMIT(C)
	 * CoordinatorState: A, C
	 */
	private static void doPolling(final MobileClient2PC client) {
		Thread t = new Thread() {
			public void run() {
				
				boolean isDataLoadDone = false;
				boolean isVoteSent = false;
				int pollContinueCounter = 0;
				
				do {

					if (client.coordinator.isDataLoadInitiated()) {
						logger.info("Data load is initiated");
						
						if (!isDataLoadDone) {
							
							loadDataFromServer();

							logger.info("DONE: Data transferred");
							isDataLoadDone = true;
						}

						if (!isVoteSent) {
							Scanner sc = new Scanner(System.in);
							System.out.println("Would you like to abort or commit the change to local database? (A/C)");
							String enteredInput = sc.nextLine();
							if (enteredInput.equalsIgnoreCase("C"))
								client.coordinator.setClientPoll(client.clientID, "C");
							else 
								client.coordinator.setClientPoll(client.clientID, "A");
							logger.info("SENT: Vote");
							isVoteSent = true;
						}
						else {
							logger.info("What is the coordinated status at server?");
							String coordinatedPollState = client.coordinator.getCoordinatedPoll();
							logger.info(coordinatedPollState);
							
							if (coordinatedPollState.equalsIgnoreCase("C")) {
								try {
									MobileUtility.saveDataToLocal(client.loadedDataFromServer);
								} catch (Exception e) {
									e.printStackTrace();
								}
								logger.info("DONE: Commit");
								client.coordinator.acknowledgeOperation(client.clientID);
								break;
							}
							else if (coordinatedPollState.equalsIgnoreCase("A")) {
								logger.info("DONE: Aborted");
								client.coordinator.acknowledgeOperation(client.clientID);
								break;
							}
							else {
								pollContinueCounter++;
								logger.info("POLL: Going on");
								if (pollContinueCounter > 10) { // timeout set
									client.coordinator.setClientCrashed(true);
									client.coordinator.acknowledgeOperation(client.clientID);
									System.out.println("Aborted suspecting client crash");
									break;
								}
							}
						}
					} else {
						logger.info("Data load not initiated");
					}
					try {
						Thread.sleep(10000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (true);
			}
			private void loadDataFromServer() {
				client.loadedDataFromServer = client.coordinator.loadData();
			}
		};
		t.start();
	}
}
