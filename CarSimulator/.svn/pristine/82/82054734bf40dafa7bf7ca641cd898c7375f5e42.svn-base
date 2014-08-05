package de.tudarmstadt.kom.obd;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.util.Log;
import de.tudarmstadt.kom.carsimulator.CarSimulatorMain;
import de.tudarmstadt.kom.carsimulator.SimulationEngineAPI;
import de.tudarmstadt.kom.obd.captureingUtilities.OBDDataContainer;
import de.tudarmstadt.kom.obd.captureingUtilities.OBDDataRecord;

public class OBD {

	//-----------------------------------
	private int sleepTimeOnSearch = 10000;
	private int sleepTimeConnected = 60000;
	private String bt_adapter1 = "APOS BT OBD 327";
	private String bt_adapter2 = "CBT.";
	private String[] supportedAdapters = {bt_adapter1, bt_adapter2};
	private SimulationEngineAPI _engine;
	private boolean _simulated;

	//-----------------------------------

	public static boolean isConnected = false;
	private HashMap<String, String> availableAdapters = new HashMap<String,String>();


	private Thread t;
	private OBDConnection obd;

	private BluetoothDevice _dev = null;
	private BluetoothSocket _sock = null;
	private Context context;

	private BluetoothAdapter mBtAdapter;
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	private static IntentFilter filterBT;



	public OBD(Context context, boolean pSimulated){
		_simulated = pSimulated;
		if (_simulated) {
			_engine = CarSimulatorMain.getConnection().getApi();
		} else {
			this.context = context;
			connect();
		}
	}

	private void connect(){
		Log.d("obd", "connect");
		t = new Thread(){

			@Override
			public void run() {
				while(true){
					if(isConnected){

						try {
							Log.d("obd", "connected");
							Thread.sleep(sleepTimeConnected);
						} catch (InterruptedException e) {
							Log.e("obd", e.getMessage());
						}
					}
					else
						try {
							Log.d("obd", "try to connect");
							tryConnection();
							Log.d("obd", "sleep");
							Thread.sleep(sleepTimeOnSearch);
						} catch (InterruptedException e) {
							Log.e("obd", e.getMessage());
						}
				}
			}
		};
		t.start();
	}

	private void tryConnection(){
		mBtAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBtAdapter == null) {
			isConnected = false;
		} else {

			if (mBtAdapter.isEnabled()) {
				Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
				if (pairedDevices.size() > 0) {
					for (BluetoothDevice device : pairedDevices) {
						availableAdapters.put(device.getName(), device.getAddress());
					}
					for (int k=0; k< supportedAdapters.length ;k++){
						if (availableAdapters.containsKey(supportedAdapters[k]) && isConnected==false){
							_dev = mBtAdapter.getRemoteDevice(availableAdapters.get(supportedAdapters[k]));
							try {
								_sock = _dev.createRfcommSocketToServiceRecord(MY_UUID);
								if (_sock.isConnected()==false){
									try{
										_sock.connect();
										filterBT = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);									 
										ConnectionReceiver mReceiver = new ConnectionReceiver();
										context.registerReceiver(mReceiver, filterBT);
									}catch(IOException e){
										Log.d("obd", "BT socket closed, adapter offline");
									}						
								}
								if (_sock.isConnected() && isConnected==false){
									obd = new OBDConnection(_sock);
									obd.startOBDConnection();
									isConnected=true;
									Log.i("obd", "OBD BT Adapter " + supportedAdapters[k] + " connected");
								}
							} catch (Exception e) {
								Log.e("obd", e.getMessage());
							}
						}
					}
				}
			}	
		}
	}

	class ConnectionReceiver extends BroadcastReceiver {     
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
				Log.i("obd", "Disconnection detected: " + action);
				isConnected = false;
			}
		}
	}

	public double getValue(String signal) {
		if (!_simulated) {
			double value = -1;
			if (PID.containsPid(signal)) {
				PID pid = PID.get(signal);
				try {
					if (obd.checkPID(pid)) {
//						PID[] pid_set = new PID[1];
//						pid_set[0] = pid;
						value = obd.readPIDs(pid)[0];
					}
				} catch (IOException e) {
					Log.e("obd", e.getMessage());
				} catch (InterruptedException e) {
					Log.e("obd", e.getMessage());
				}
			}
			return value;
		} else {
			try {
				return _engine.getValue(signal);
			} catch (RemoteException e) {

			}
		}
		return -1;
	}

	public Boolean adapterConnected() {
		return isConnected;
	}

	public int getCurrentSpeedLimit() {
		if (!_simulated) {
			return 0;
		}
		try {
			return _engine.getCurrentSpeedLimit();
		} catch (RemoteException e) {

		}
		return -1;
	}

}
