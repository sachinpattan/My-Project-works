package de.tudarmstadt.kom.obd;

public enum OBDCommand {

	
	RESET		("AT Z"),
	ECHO		("AT E0"),
	LINEFEED_OFF ("AT L0"),
	TIMEOUT 	("AT ST " + Integer.toHexString(0xFF & 62)),
	PROTOCOL	("AT SP " + 0);
	
	private String cmd;
	
	private OBDCommand(String cmd) {
		this.cmd = cmd;
	}
	
	public String get(){
		return cmd;
	}
}
