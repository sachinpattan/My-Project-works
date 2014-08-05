
package de.tudarmstadt.kom.obd.captureingUtilities;

import java.util.Random;

import android.util.Log;
import de.tudarmstadt.kom.obd.OBD;
import de.tudarmstadt.kom.obd.PID;


public class CaptureOBDData {
	private Thread _thread;
	private OBD _oBD;
	private OBDDataContainer _container;
	
	public CaptureOBDData(OBD pOBD) {
		_oBD = pOBD;
		_container = OBDDataContainer.getOnlyInstance();
	}
	
	public CaptureOBDData() {
		_container = OBDDataContainer.getOnlyInstance();
	}
	
	public boolean isRunning() {
		return _thread != null && _thread.isAlive();
	}
	
	public void receiveSensorData(final int pSpeed) {
		_thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				if (_container.getRecords(pSpeed).getPidData().size() != 0) {
					_container.getRecords(pSpeed).getPidData().clear();
				}
				if (_oBD != null) {
					for (PID pid : PID.values()) {
						try {
							String id = pid.getPid();
							double value = _oBD.getValue(id);
							Log.d("obd", "Receiving data for " + pid.getPid() + " and speedlimit " + pSpeed + " value " + value);
							_container.addRecord(pSpeed, id, value);
						} catch (Exception e) {
							
						}
					}
				} else {
					Random random = new Random();
					for (PID pid : PID.values()) {
						try {
							String id = pid.getPid();
							double value = random.nextDouble();
							Log.d("obd", "Receiving data for " + pid.getPid() + " and speedlimit " + pSpeed + " value " + value);
							_container.addRecord(pSpeed, id, value);
						} catch (Exception e) {
							
						}
					}
				}
				saveDataToFilesystem();
			}
		});
		_thread.start();
	}
	
	public boolean saveDataToFilesystem() {
		Log.d("obd", "Saving to file system");
		return _container.saveRecords();
	}
}
