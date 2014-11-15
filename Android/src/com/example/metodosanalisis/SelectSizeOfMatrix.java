package com.example.metodosanalisis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SelectSizeOfMatrix extends Activity {

	EditText size;
	int method;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_size_of_matrix);
		size = (EditText) findViewById(R.id.sizeOfMatrix);
		method = this.getIntent().getExtras().getInt("nextMethod");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home /Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void goToMethod(View v) {

		int matrixSize = Integer.parseInt(size.getText().toString());
		Intent nextMethod = null;
		switch (method) {
		case 1:
			nextMethod = new Intent(this, Eliminacion_Gauss.class);
			nextMethod.putExtra("size", matrixSize);
			break;
		case 2:
			nextMethod = new Intent(this, Factorizacion.class);
			nextMethod.putExtra("size", matrixSize);
			Log.i("no", "no");
			break;
		case 3:
			nextMethod = new Intent(this, Metodos_Iterativos.class);
			nextMethod.putExtra("size", matrixSize);
			Log.i("si", "si");
			break;
		default:
			break;
		}
		
		startActivity(nextMethod);

	}
}
