package com.example.metodosanalisis;


import android.R.interpolator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SelectNumberOfPoints extends Activity {
	EditText points;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_number_of_points);
		points = (EditText)findViewById(R.id.numberOfPoints);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_number_of_points, menu);
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
	
	
	public void goToMethod(View v){
		
		Intent nextMethod = new Intent(this, Interpolacion.class);
		nextMethod.putExtra("numberOfPoints", Integer.parseInt(points.getText().toString()));
		startActivity(nextMethod);
	}
}
