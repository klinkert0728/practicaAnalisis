package com.example.metodosanalisis;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void UnaVar(View v){
		Intent IngresarUnaVariable = new Intent(this,UnaVariable.class);
		startActivity(IngresarUnaVariable);
	}
	
	public void SistEcua(View v){
		Intent IngresarEcuaciones = new Intent(this,SistemasDeEcuaciones.class);
		startActivity(IngresarEcuaciones);
	}
	public void Interp(View v){
		Intent IngresarInterp = new Intent(this,Interpolacion.class);
		startActivity(IngresarInterp);
	}
	public void Integ(View v){
		Intent IngresarInterpInteg = new Intent(this,InterpolacionIntegracion.class);
		startActivity(IngresarInterpInteg);
	}
}
