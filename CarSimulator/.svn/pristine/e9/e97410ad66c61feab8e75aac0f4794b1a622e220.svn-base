package de.tudarmstadt.kom.carsimulator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

import de.tudarmstadt.kom.carsimulator.xml.route.KoordinatesWaypoint;

public class GpsCarPositionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps_car_position);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gps_car_position, menu);
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
	public static class PlaceholderFragment extends Fragment implements Runnable {
		private MapView mapView;
		private GoogleMap map;
		private volatile GroundOverlay _car;
		private Activity _activity;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.fragment_gps_car_position, container, false);
			// Gets the MapView from the XML layout and creates it
			_activity = getActivity();
			mapView = (MapView) v.findViewById(R.id.map);
			mapView.onCreate(savedInstanceState);
			// Gets to GoogleMap from the MapView and does initialization stuff
			map = mapView.getMap();
			map.getUiSettings().setMyLocationButtonEnabled(false);

			// Needs to call MapsInitializer before doing any CameraUpdateFactory calls
			try {
				MapsInitializer.initialize(this.getActivity());
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread thread = new Thread(this);
			thread.start();
			return v;
		}

		@Override
		public void onResume() {
			mapView.onResume();
			super.onResume();
		}
		@Override
		public void onDestroy() {
			super.onDestroy();
			mapView.onDestroy();
		}
		@Override
		public void onLowMemory() {
			super.onLowMemory();
			mapView.onLowMemory();
		}

		@Override
		public void run() {
			SimulationEngineAPI engine = CarSimulatorMain.getConnection().getApi();
			try {
				while (true) {
					if (engine.isStarted()) {
						KoordinatesWaypoint koordinaten = engine.getCurrentPosition();
						final LatLng position = new LatLng(koordinaten.getLatitude(), koordinaten.getLongitude());
						if (_car == null) {
							_activity.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									GroundOverlayOptions carIcon = new GroundOverlayOptions();
									carIcon.image(BitmapDescriptorFactory.fromResource(R.drawable.auto));
									carIcon.position(position, 64f, 64f);
									_car = map.addGroundOverlay(carIcon);
									CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 15);
									map.moveCamera(cameraUpdate);
								}
							});
						} else {
							_activity.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									_car.setPosition(position);
									map.stopAnimation();
									CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 15);
									//								map.moveCamera(cameraUpdate);
									map.animateCamera(cameraUpdate, 500, null);
								}
							});
						}
					} else {
						if (_car != null) {
							_activity.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									_car.remove();
									_car = null;
								}
							});
						}
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (RemoteException e) {

			}

		}
	}

}
