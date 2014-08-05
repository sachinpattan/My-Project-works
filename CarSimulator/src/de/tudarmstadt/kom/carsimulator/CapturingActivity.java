package de.tudarmstadt.kom.carsimulator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import de.tudarmstadt.kom.obd.OBD;
import de.tudarmstadt.kom.obd.captureingUtilities.CaptureOBDData;

public class CapturingActivity extends Activity {
	private OBD _oBD;
	private CaptureOBDData _captureOBDData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		_oBD = new OBD(getApplicationContext());
		_captureOBDData = new CaptureOBDData(new OBD(getApplicationContext(), false));
		setContentView(R.layout.activity_capturing);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.capturing, menu);
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
	
	public void startCapture(View view) {
		final CapturingActivity capturing = this;

		EditText mEdit;
		if (_captureOBDData.isRunning()) {
			Toast.makeText(capturing, "The App is busy receiving status please wait for some time.", Toast.LENGTH_SHORT).show();
		} else {
			mEdit = (EditText) findViewById(R.id.speedLimit);
			String speedLimit = mEdit.getText().toString();
			if (isInteger(speedLimit)) {
				if (_oBD == null || _oBD.adapterConnected()) {
					_captureOBDData.receiveSensorData(Integer.parseInt(speedLimit));
					Thread thread = new Thread(new Runnable() {
						
						@Override
						public void run() {
							do {
								try {
									Thread.sleep(250);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							} while (_captureOBDData.isRunning());
							capturing.runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(capturing, "The app has finished the capture of the sensor data.", Toast.LENGTH_SHORT).show();
								}
							});
							
						}
					});
					thread.start();
				} else {
					Toast.makeText(capturing, "Please wait till the mobile has connected and then try again.", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(capturing, "Please enter the speed limit in integer format.", Toast.LENGTH_SHORT).show();
			}
		}

	}

	private boolean isInteger(String speedLimit) {
		try {
			Integer.parseInt(speedLimit);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
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
			View rootView = inflater.inflate(R.layout.fragment_capturing,
					container, false);
			return rootView;
		}
	}

}
