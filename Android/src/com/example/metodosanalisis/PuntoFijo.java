package com.example.metodosanalisis;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PuntoFijo extends Activity {
	EditText Xu,itera, Tol, f, g, resultadoPF;
	evaluadorFunciones e = new evaluadorFunciones();
	double xu, tols;
	Float x1, tol;
	int iter;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.punto_fijo, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_punto_fijo);
		
		Xu =  (EditText) findViewById (R.id.X1PF);
		Tol =   (EditText) findViewById (R.id.tolPF);
		f = (EditText) findViewById (R.id.funcionPF);
		g = (EditText) findViewById (R.id.GPF);
		itera = (EditText) findViewById (R.id.IteraPF);
		resultadoPF = (EditText) findViewById (R.id.resultadoPF);
		Button cal = (Button) findViewById(R.id.calcularPF);
		
		
			if(Xu.getText().toString().equals("")){
				x1 = 0f;
			}else	{
				x1= Float.valueOf(Xu.getText().toString());
				if(Tol.getText().toString().equals("")){
					tol= 0f;
				}else	{
					tol= Float.valueOf(Tol.getText().toString());

				}
			}

		cal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				double res= 0.0;
				//obtenemos cada valor de los campos.
				if(!itera.getText().toString().equals("")){
					iter = Integer.parseInt(itera.getText().toString());

				}
				if((!Xu.getText().toString().equals(""))){
					xu = Double.parseDouble(Xu.getText().toString());
				}

				if((!Tol.getText().toString().equals(""))){
					tols = Double.parseDouble(Tol.getText().toString());
				}
				
				
				try {
					e.setFunction(g.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//implemento el metodo punto fijo
				int cont =0;
				double error = tols + 1;
				double gx = e.calculate(xu);
				while(cont < iter && error > tols && gx != 0){
					gx = e.calculate(xu);
					error = ((Math.abs(xu - gx))/xu)*100;
					xu = gx;
					cont++;
						
				}
				if(gx == 0){
					resultadoPF.setText(String.valueOf(xu));
				}else{
					if(error < tols){
						resultadoPF.setText(String.valueOf(xu));
					}else{
						resultadoPF.setText("no es suficiente con "+ iter +" iteraciones");
					}
				}
				
			}

		});
		
	}



}
