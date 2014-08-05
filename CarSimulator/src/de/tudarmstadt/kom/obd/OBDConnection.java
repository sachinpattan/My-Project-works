package de.tudarmstadt.kom.obd;

import java.io.IOException;

import android.bluetooth.BluetoothSocket;
import android.util.Log;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

class OBDConnection {

	private BTConnection myBTConnection;
	
	
	private String command_reset = "AT Z";
	private String command_echo = "AT E0";
	private String command_lineFeedOff = "AT L0";
	private String command_timeout = "AT ST " + Integer.toHexString(0xFF & 62);
	private String command_protocol = "AT SP " + 0;
	
	public OBDConnection(BluetoothSocket socket){
		myBTConnection = new BTConnection(socket);
	}
	
	public void startOBDConnection() throws IOException, InterruptedException{
		myBTConnection.requestData(command_reset);
		myBTConnection.requestData(command_echo);
		myBTConnection.requestData("AT E0");
		myBTConnection.requestData(command_lineFeedOff);
		myBTConnection.requestData(command_timeout);
		myBTConnection.requestData(command_protocol);
	}
	
	public int getMotorType() throws IOException, InterruptedException{
		PID pid51 = PID.get("51");
		String data = myBTConnection.requestData(pid51.getPid());
		data = data.substring(2);
		int type = Integer.parseInt(data);
		return type;
	}
	
	private int[][] decodeMessage(String message, PID[] pids){
		int temp[][] = new int[pids.length][2];
		for(int i=0; i<pids.length; i++){
			if(message.substring(0, 2).equals(pids[i].getPid())){
				for(int j=0; j<pids[i].getBytes(); j++){
					temp[i][j] = Integer.parseInt(message.substring(2+2*j, 4+2*j), 16);
					if(j == pids[i].getBytes()-1)
						message = message.substring(4+2*j, message.length());
				}
			}
			else{
			if(message.charAt(2) == ':'){
				message = message.substring(3, message.length());
				for(int j=0; j<pids[i].getBytes(); j++){
					temp[i][j] = Integer.parseInt(message.substring(2+2*j, 4+2*j), 16);
					if(j == pids[i].getBytes()-1)
						message = message.substring(4+2*j, message.length());
				}
			}
			}
		}
		return temp;
	}
	
	public boolean checkPID(PID pid) throws IOException, InterruptedException{
		System.out.println("Pid being checked : "+pid);
		String supported = "";
		System.out.println(Integer.parseInt(pid.getPid(), 16));
		if(Integer.parseInt(pid.getPid(), 16) > 0)
			supported = "00";
		if(Integer.parseInt(pid.getPid(), 16) > 32)
			supported = "20";
		if(Integer.parseInt(pid.getPid(), 16) > 64)
			supported = "40";
		if(Integer.parseInt(pid.getPid(), 16) > 96)
			supported = "60";
		if (supported.equals("")) {
			return true;
		}
		String data = myBTConnection.requestData("01" + supported);
		Log.d("obd", "support: " + data);
		System.out.println(data);
		data = data.substring(2, data.length());
		
		if (data.contains("UNABLETOCONNECT"))
			return false;
		
		String data1 = data.substring(0, 2);
		String data2 = data.substring(2, 4);
		String data3 = data.substring(4, 6);
		String data4 = data.substring(6, 8);
		
		int i1 = Integer.parseInt(data1, 16);
		String Bin1 = Integer.toBinaryString(i1);
		int i2 = Integer.parseInt(data2, 16);
		String Bin2 = Integer.toBinaryString(i2);
		int i3 = Integer.parseInt(data3, 16);
		String Bin3 = Integer.toBinaryString(i3);
		int i4 = Integer.parseInt(data4, 16);
		String Bin4 = Integer.toBinaryString(i4);
	
		if(Bin1.length() < 8){
			String temp = "";
			for(int i=0; i<8-Bin1.length(); i++)
				temp = temp + "0";
			Bin1 = temp + Bin1;
		}
		if(Bin2.length() < 8){
			String temp = "";
			for(int i=0; i<8-Bin2.length(); i++)
				temp = temp + "0";
			Bin2 = temp + Bin2;
		}
		if(Bin3.length() < 8){
			String temp = "";
			for(int i=0; i<8-Bin3.length(); i++)
				temp = temp + "0";
			Bin3 = temp + Bin3;
		}
		if(Bin4.length() < 8){
			String temp = "";
			for(int i=0; i<8-Bin4.length(); i++)
				temp = temp + "0";
			Bin4 = temp + Bin4;
		}
			
		
		String data_toCheck = Bin1 + Bin2 + Bin3 + Bin4;
	    
	    int pos = Integer.parseInt(pid.getPid(), 16) - Integer.parseInt(supported, 16);
	    if(data_toCheck.charAt(pos-1) == '1')
	    	return true;
	    else
	    	return false;
	}
	
	public double[] readPIDs(PID...pids) throws IOException, InterruptedException{
		String command = "";
		for (int i=0; i<pids.length; i++){
			command = command + pids[i].getPid();
		}
		Log.d("obd", "Command!:" + command);
		String message = myBTConnection.requestData("01" + command);
		int[][] temp = new int[pids.length][2];
		temp = decodeMessage(message, pids);
		double[] result = new double[pids.length];

		
		for(int i=0; i<pids.length; i++){
			String infix = pids[i].getFormula();
			if(infix.contains("A"))
				infix = infix.replace("A", String.valueOf(temp[i][0]));
			if(infix.contains("B"))
				infix = infix.replace("B", String.valueOf(temp[i][1]));
			Calculable calc;
			try {
				calc = new ExpressionBuilder(infix).build();
				result[i] = calc.calculate();
			} catch (UnknownFunctionException e) {
				e.printStackTrace();
			} catch (UnparsableExpressionException e) {
				e.printStackTrace();
			}				
		}
		return result;
	}
	
	
}
