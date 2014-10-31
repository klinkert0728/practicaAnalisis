package com.example.metodosanalisis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class SistemasDeEcuaciones extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sistemas_de_ecuaciones);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sistemas_de_ecuaciones, menu);
		return true;
	}
	
	public void gauss(View v){
		Log.i("marica", "marica");
		Intent gauss = new Intent(SistemasDeEcuaciones.this, Eliminacion_Gauss.class);
		startActivity(gauss);
	}
	
	public void factorizacion(View v){
		Intent factorizacion = new Intent(SistemasDeEcuaciones.this,Factorizacion.class );
		startActivity(factorizacion);
	}
	
	public void iterativos(View v){
		Intent iterativos = new Intent(SistemasDeEcuaciones.this, Metodos_Iterativos.class);
		startActivity(iterativos);
	}

}
