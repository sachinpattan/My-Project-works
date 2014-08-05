package de.tudarmstadt.kom.obd.captureingUtilities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public class Pid {

	@Element
	String id;
	@Element
	Double value;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	

}
