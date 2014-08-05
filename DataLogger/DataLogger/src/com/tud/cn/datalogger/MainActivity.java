package com.tud.cn.datalogger;

import com.tud.cn.datalogger.services.MyService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	// Start the  service
	public void startNewService(View view) {
		Log.d("Activity", "Start Clicked!");
		startService(new Intent(this, MyService.class));
	}

	// Stop the  service
	public void stopNewService(View view) {

		stopService(new Intent(this, MyService.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
