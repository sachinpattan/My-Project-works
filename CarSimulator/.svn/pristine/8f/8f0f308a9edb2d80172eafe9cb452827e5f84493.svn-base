package de.tudarmstadt.kom.obd.captureingUtilities;

import java.util.HashMap;
import java.util.Map;




public class OBDDataRecord {
	private Map<String, Double> _pidData;
//	private String _pid;
//	private double _data;
	
	public OBDDataRecord() {
		_pidData = new HashMap<>();
	}
	
	public double getPIDValue(String pPID) {
		return _pidData.get(pPID);
	}
	
	public Map<String, Double> getPidData() {
		return _pidData;
	}
}
