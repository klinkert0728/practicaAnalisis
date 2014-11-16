package com.example.metodosanalisis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class InterpolacionIntegracion extends Activity {
	
	CheckBox simpson13;
	CheckBox simpson18;
	CheckBox trapecioG;
	EditText particiones;
	EditText formula;
	EditText lInf;
	EditText lSup;
	EditText derivada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interpolacion_integracion);
		
		simpson13	=	(CheckBox)findViewById(R.id.simpson13);
		simpson18	=	(CheckBox)findViewById(R.id.simpson18);
		trapecioG	=	(CheckBox)findViewById(R.id.trapecio);
		particiones =	(EditText)findViewById(R.id.particiones);
		formula		=	(EditText)findViewById(R.id.formula);
		lInf		=	(EditText)findViewById(R.id.Linf);
		lSup		=	(EditText)findViewById(R.id.Lsup);
		derivada	=	(EditText)findViewById(R.id.derivada);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interpolacion_integracion, menu);
		return true;
	}
	
	public void calcular(View v){
		Intent next = new Intent(this,SolucionIntegracion.class);
		next.putExtra("particiones", Integer.parseInt(particiones.getText().toString()));
		next.putExtra("LimSup", Integer.parseInt(lSup.getText().toString()));
		next.putExtra("LimInf", Integer.parseInt(lInf.getText().toString()));
		next.putExtra("Formula",formula.getText().toString());
		if (simpson13.isChecked() && !simpson18.isChecked() && !trapecioG.isChecked()){
			
			next.putExtra("method", 1);
			startActivity(next);
			
		}else if (!simpson13.isChecked() && simpson18.isChecked() && !trapecioG.isChecked()){
			next.putExtra("method", 2);
			startActivity(next);
		}else if (!simpson13.isChecked() && !simpson18.isChecked() && trapecioG.isChecked()){
			next.putExtra("method", 3);
			next.putExtra("Derivada",derivada.getText().toString());
			startActivity(next);
			
		}
		
		
	}

}
