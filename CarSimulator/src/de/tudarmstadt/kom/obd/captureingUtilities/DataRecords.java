package de.tudarmstadt.kom.obd.captureingUtilities;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class DataRecords {

	@ElementList
	List<DataRecord> records;
	public List<DataRecord> getRecords() {
		if (records == null) {
			synchronized (DataRecords.class) {
				if (records == null) {
					records = new ArrayList<>();
				}
			}
		}
		return records;
	}
	public void setRecords(List<DataRecord> records) {
		this.records = records;
	}

}
