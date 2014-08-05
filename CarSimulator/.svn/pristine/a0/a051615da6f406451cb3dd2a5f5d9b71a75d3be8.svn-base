package de.tudarmstadt.kom.obd.captureingUtilities;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

public class DataRecord {

	@Element
	Integer speed;
	@ElementList
	List<Pid> pids;
	public List<Pid> getPids() {
		return pids;
	}
	public void setPids(List<Pid> pids) {
		this.pids = pids;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
}
