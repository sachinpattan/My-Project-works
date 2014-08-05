package de.tudarmstadt.kom.obd;

public enum PID {

	_00 ("00", "PIDs supported [01 - 20]", "", 4, ""),
	_01 ("01", "Monitor status since DTCs cleared. (Includes malfunction indicator lamp (MIL) status and number of DTCs.)", "", 4, ""),
	_02 ("02", "Freeze DTC", "", 2, ""),
	_03 ("03", "Fuel system status", "", 2, ""),
	_04 ("04", "Calculated engine load value", "%", 1, "A*100/255"),
	_05 ("05", "Engine coolant temperature", "??C", 1, "A-40"),
	_06 ("06", "Short term fuel % trim??Bank 1", "%", 1, "(A-128)*100/128"),
	_07 ("07", "Long term fuel % trim??Bank 1", "%", 1, "(A-128)*100/128"),
	_08 ("08", "Short term fuel % trim??Bank 2", "%", 1, "(A-128)*100/128"),
	_09 ("09", "Long term fuel % trim??Bank 2", "%", 1, "(A-128)*100/128"),
	_0a ("0A", "Fuel pressure", "kPa", 1, "A*3"),
	_0b ("0B", "Intake manifold absolute pressure", "kPa", 1, "A"),
	_0c ("0C", "Engine RPM", "rpm", 2, "((A*256)+B)/4"),
	_0d ("0D", "Vehicle speed", "km/h", 1, "A"),
	_0e ("0E", "Timing advance", "??", 1, "(A-128)/2"),
	_0f ("0F", "Intake air temperature", "??C", 1, "A-40"),
	_10 ("10", "MAF air flow rate", "grams/sec", 2, "((A*256)+B)/100"),
	_11 ("11", "Throttle position", "%", 1, "A*100/255"),
	_12 ("12", "Commanded secondary air status", "", 1, ""),
	_13 ("13", "Oxygen sensors present", "", 1, ""),
	_14 ("14", "Bank 1, Sensor 1:Oxygen sensor voltage", "Volts", 2, "A/200"),
	_15 ("15", "Bank 1, Sensor 2:Oxygen sensor voltage", "Volts", 2, "A/200"),
	_16 ("16", "Bank 1, Sensor 3:Oxygen sensor voltage", "Volts", 2, "A/200"),
	_17 ("17", "Bank 1, Sensor 4:Oxygen sensor voltage", "Volts", 2, "A/200"),
	_18 ("18", "Bank 2, Sensor 1:Oxygen sensor voltage", "Volts", 2, "A/200"),
	_19 ("19", "Bank 2, Sensor 2:Oxygen sensor voltage", "Volts", 2, "A/200"),
	_1a ("1A", "Bank 2, Sensor 3:Oxygen sensor voltage", "Volts", 2, "A/200"),
	_1b ("1B", "Bank 2, Sensor 4:Oxygen sensor voltage", "Volts", 2, "A/200"),
	_1c ("1C", "OBD standards this vehicle conforms to", "", 1, ""),
	_1d ("1D", "Oxygen sensors present", "", 1, ""),
	_1e ("1E", "Auxiliary input status", "", 1, ""),
	_1f ("1F", "Run time since engine start", "seconds", 2, "(A*256)+B"),
	_20 ("20", "PIDs supported [21 - 40]", "", 4, ""),
	_21 ("21", "Distance traveled with malfunction indicator lamp (MIL) on", "km", 2, "(A*256)+B"),
	_22 ("22", "Fuel Rail Pressure (relative to manifold vacuum)", "kPa", 2, "((A*256)+B) * 0.079"),
	_23 ("23", "Fuel Rail Pressure (diesel, or gasoline direct inject)", "kPa", 2, "((A*256)+B) * 10"),
	_24 ("24", "O2S1_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)*2/65535"),
	_25 ("25", "O2S2_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)*2/65535"),
	_26 ("26", "O2S3_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)*2/65535"),
	_27 ("27", "O2S4_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)*2/65535"),
	_28 ("28", "O2S5_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)*2/65535"),
	_29 ("29", "O2S6_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)*2/65535"),
	_2a ("2A", "O2S7_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)*2/65535"),
	_2b ("2B", "O2S8_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)*2/65535"),
	_2c ("2C", "Commanded EGR", "%", 1, "A*100/255"),
	_2d ("2D", "EGR Error", "%", 1, "(A-128) * 100/128"),
	_2e ("2E", "Commanded evaporative purge", "%", 1, "A*100/255"),
	_2f ("2F", "Fuel Level Input", "%", 1, "A*100/255"),
	_30 ("30", "# of warm-ups since codes cleared", "N/A", 1, "A"),
	_31 ("31", "Distance traveled since codes cleared", "km", 2, "(A*256)+B"),
	_32 ("32", "Evap. System Vapor Pressure", "Pa", 2, "((A*256)+B)/4"),
	_33 ("33", "Barometric pressure", "kPa", 1, "A"),
	_34 ("34", "O2S1_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)/32,768"),
	_35 ("35", "O2S2_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)/32,768"),
	_36 ("36", "O2S3_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)/32,768"),
	_37 ("37", "O2S4_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)/32,768"),
	_38 ("38", "O2S5_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)/32,768"),
	_39 ("39", "O2S6_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)/32,768"),
	_3a ("3A", "O2S7_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)/32768"),
	_3b ("3B", "O2S8_WR_lambda(1):Equivalence Ratio", "N/A", 4, "((A*256)+B)/32768"),
	_3c ("3C", "Catalyst Temperature Bank 1, Sensor 1", "??C", 2, "((A*256)+B)/10 - 40"),
	_3d ("3D", "Catalyst Temperature Bank 2, Sensor 1", "??C", 2, "((A*256)+B)/10 - 40"),
	_3e ("3E", "Catalyst Temperature Bank 1, Sensor 2", "??C", 2, "((A*256)+B)/10 - 40"),
	_3f ("3F", "Catalyst Temperature Bank 2, Sensor 2", "??C", 2, "((A*256)+B)/10 - 40"),
	_40 ("40", "PIDs supported [41 - 60]", "", 4, ""),
	_41 ("41", "Monitor status this drive cycle", "", 4, ""),
	_42 ("42", "Control module voltage", "V", 2, "((A*256)+B)/1000"),
	_43 ("43", "Absolute load value", "%", 2, "((A*256)+B)*100/255"),
	_44 ("44", "Command equivalence ratio", "N/A", 2, "((A*256)+B)/32768"),
	_45 ("45", "Relative throttle position", "%", 1, "A*100/255"),
	_46 ("46", "Ambient air temperature", "??C", 1, "A-40"),
	_47 ("47", "Absolute throttle position B", "%", 1, "A*100/255"),
	_48 ("48", "Absolute throttle position C", "%", 1, "A*100/255"),
	_49 ("49", "Absolute throttle position D", "%", 1, "A*100/255"),
	_4a ("4A", "Absolute throttle position E", "%", 1, "A*100/255"),
	_4b ("4B", "Absolute throttle position F", "%", 1, "A*100/255"),
	_4c ("4C", "Commanded throttle actuator", "%", 1, "A*100/255"),
	_4d ("4D", "Time run with MIL on", "minutes", 2, "(A*256)+B"),
	_4e ("4E", "Time since trouble codes cleared", "minutes", 2, "(A*256)+B"),
	_4f ("4F", "Maximum value for equivalence ratio, oxygen sensor voltage, oxygen sensor current, and intake manifold absolute pressure", "", 4, "A"),
	_50 ("50", "Maximum value for air flow rate from mass air flow sensor", "g/s", 4, "A*10"),
	_51 ("51", "Fuel Type", "", 1, ""),
	_52 ("52", "Ethanol fuel %", "%", 1, "A*100/255"),
	_53 ("53", "Absolute Evap system Vapor Pressure", "kPa", 2, "((A*256)+B)/200"),
	_54 ("54", "Evap system vapor pressure", "Pa", 2, "((A*256)+B)-32767"),
	_55 ("55", "Short term secondary oxygen sensor trim bank 1 and bank 3", "%", 2, "(A-128)*100/128"),
	_56 ("56", "Long term secondary oxygen sensor trim bank 1 and bank 3", "%", 2, "(A-128)*100/128"),
	_57 ("57", "Short term secondary oxygen sensor trim bank 2 and bank 4", "%", 2, "(A-128)*100/128"),
	_58 ("58", "Long term secondary oxygen sensor trim bank 2 and bank 4", "%", 2, "(A-128)*100/128"),
	_59 ("59", "Fuel rail pressure (absolute)", "kPa", 2, "((A*256)+B) * 10"),
	_5a ("5A", "Relative accelerator pedal position", "%", 1, "A*100/255"),
	_5b ("5B", "Hybrid battery pack remaining life", "%", 1, "A*100/255"),
	_5c ("5C", "Engine oil temperature", "??C", 1, "A + 40"), //was former A - 40, but in comparison to the motor temperature we decided that it is A + 40.
	_5d ("5D", "Fuel injection timing", "??", 2, "(((A*256)+B)-26880)/128"),
	_5e ("5E", "Engine fuel rate", "L/h", 2, "((A*256)+B)*0.05"),
	_5f ("5F", "Emission requirements to which vehicle is designed", "", 1, ""),
	_60 ("60", "PIDs supported [61 - 80]", "", 4, ""),
	_61 ("61", "Driver's demand engine - percent torque", "%", 1, "A-125"),
	_62 ("62", "Actual engine - percent torque", "%", 1, "A-125"),
	_63 ("63", "Engine reference torque", "Nm", 2, "A*256+B"),
	_64 ("64", "Engine percent torque data", "%", 5, "A-125");
	
	private String pid;
	private String p_name;
	private String unit;
	private int bytes;
	private String formula;
	
	private PID(String pid, String p_name, String unit, int bytes, String formula){
		setPid(pid);
		setP_name(p_name);
		setUnit(unit);
		setBytes(bytes);
		setFormula(formula);
	}

	public String getPid() {
		return pid;
	}

	public String getP_name() {
		return p_name;
	}

	public String getUnit() {
		return unit;
	}

	public int getBytes() {
		return bytes;
	}

	public String getFormula() {
		return formula;
	}

	private void setPid(String pid) {
		this.pid = pid;
	}

	private void setP_name(String p_name) {
		this.p_name = p_name;
	}

	private void setUnit(String unit) {
		this.unit = unit;
	}

	private void setBytes(int bytes) {
		this.bytes = bytes;
	}

	private void setFormula(String formula) {
		this.formula = formula;
	}
	
	public static boolean containsPid(String pid) {
		for (PID p : PID.values()) {
			if (p.getPid().equals(pid))
				return true;
		}
		return false;
	}
	
	public static PID get(String pid){
		for (PID p : PID.values()) {
			if (p.getPid().equals(pid))
				return p;
		}
		return null;
	}
}
