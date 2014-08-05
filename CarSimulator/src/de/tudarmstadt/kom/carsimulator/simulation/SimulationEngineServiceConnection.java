package de.tudarmstadt.kom.carsimulator.simulation;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import de.tudarmstadt.kom.carsimulator.SimulationEngineAPI;

public class SimulationEngineServiceConnection implements ServiceConnection {
	private SimulationEngineAPI _api;

	@Override
	public void onServiceConnected(ComponentName pName, IBinder pService) {
		_api = SimulationEngineAPI.Stub.asInterface(pService);
		Log.d("obd", "Connected to service");
	}
	
	public SimulationEngineAPI getApi() {
		return _api;
	}
	
	@Override
	public void onServiceDisconnected(ComponentName pName) {
		_api = null;
	}

}
