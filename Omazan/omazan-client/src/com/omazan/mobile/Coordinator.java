/**
 * 
 */
package com.omazan.mobile;

import java.util.Map;

import javax.ejb.Remote;

/**
 * @author Banashri
 *
 */
@Remote
public interface Coordinator {
	
	public void connect(int clientID);
	
	public String getCoordinatedPoll();
	
	public void initDataLoad(int clientID, boolean clientPoll);
	
	public boolean isDataLoadInitiated();
	
	public void setClientPoll(int clientID, String poll);
	
	public Map loadData();
	
	public void acknowledgeOperation(int clientID);
	
	public void setClientCrashed(boolean isClientCrashed);
}
