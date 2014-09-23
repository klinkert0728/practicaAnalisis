package com.example.metodosanalisis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

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
	
	
	public void irabiseccion(View v){
		Intent biseccion = new Intent(this,biseccion.class);
		startActivity(biseccion);
	}
	
	public void irareglafalsa(View v){
		Intent reglaFalsa = new Intent(this,reglafalsa.class);
		startActivity(reglaFalsa);
	}
	public void iraPuntofijo(View v ){
		Intent PuntoFijo = new Intent(this,PuntoFijo.class);
		startActivity(PuntoFijo);
	}
	
	public void iraNewtonr(View v ){
		Intent newtonr = new Intent(this,newtonr.class);
		startActivity(newtonr);
	}
	
	public void iraSecante(View v ){
		Intent secante = new Intent(this,Secante.class);
		startActivity(secante);
	}
	
	public void iraBusqueda(View v ){
		Intent busqueda = new Intent(this,Secante.class); //change Secante for busqueda.class later
		startActivity(busqueda);
	}

}
