package com.example.metodosanalisis;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class UnaVariable extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_una_variable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.una_variable, menu);
		return true;
	}

}
