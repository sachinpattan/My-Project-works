package de.tudarmstadt.kom.carsimulator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.tudarmstadt.kom.obd.OBD;
import de.tudarmstadt.kom.obd.PID;

public class SensorDataActivity extends Activity {
	private static OBD _oBD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_data);
		_oBD = new OBD(getApplicationContext(), true);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
		//		if (savedInstanceState == null) {
		//			getFragmentManager().beginTransaction()
		//			.add(R.id.container, new PlaceholderFragment()).commit();
		//		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_sensor_data,
					container, false);
			final TextView text = (TextView) rootView
					.findViewById(R.id.sensorData);
			final TextView speedLimitSign = (TextView) rootView.findViewById(R.id.speedLimit);
			text.setMovementMethod(new ScrollingMovementMethod());
			final Activity currentActivity = this.getActivity();
			final StringBuffer sensorData = new StringBuffer();
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					SimulationEngineAPI engine = CarSimulatorMain.getConnection().getApi();
					try {
//						int speed = -1;
						while (true) {
							sensorData.delete(0, sensorData.length());
							if (engine.isStarted()) {
//								if (speed != engine.getCurrentSpeed()) {
//									speed = engine.getCurrentSpeed();
									for (PID pid : PID.values()) {
										try {
											String id = pid.getPid();
											double value = _oBD.getValue(id);
											sensorData.append("PID ").append(id).append(": ").append(value).append("\n");
										} catch (Exception e) {
										}
									}
									currentActivity.runOnUiThread(new Runnable() {

										@Override
										public void run() {
											text.setText(sensorData.toString());
											speedLimitSign.setText(String.valueOf(_oBD.getCurrentSpeedLimit()));
										}
									});
//								}
							} else {
//								speed = -1;
								sensorData.append("The simulation is not started yet.");
								currentActivity.runOnUiThread(new Runnable() {

									@Override
									public void run() {
										text.setText(sensorData.toString());
										speedLimitSign.setText("");
									}
								});
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					} catch (RemoteException e) {

					}
				}
			});
			thread.start();
			return rootView;
		}
	}


}
