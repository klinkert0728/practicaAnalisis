package com.example.metodosanalisis;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SolucionMetodosIterativos extends Activity {

	TextView sol;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solucion_metodos_iterativos);
		
		sol = (TextView) findViewById(R.id.ResultadoMI);
	}
	
	
}
