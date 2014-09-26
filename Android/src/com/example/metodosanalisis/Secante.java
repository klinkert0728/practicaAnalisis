package com.example.metodosanalisis;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Secante extends Activity {
	
	EditText X1,itera, Tol, f, g, resultadoST,X0;
	evaluadorFunciones e = new evaluadorFunciones();
	double xuST, tolsST,xcST;
	Float x1, tol,x0;
	int iter;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secante, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secante);
		X0= (EditText) findViewById (R.id.X0ST);
		X1 =  (EditText) findViewById (R.id.X1ST);
		Tol =   (EditText) findViewById (R.id.TolST);
		f = (EditText) findViewById (R.id.FuncionST);
		itera = (EditText) findViewById (R.id.IterST);
		resultadoST = (EditText) findViewById (R.id.ResultadoST);
		Button Calcularcito = (Button) findViewById (R.id.CalculaST);
		
		if (X0.getText().toString().equals("")) {
			x0= 0f;
		}else{
			x0= Float.valueOf(X0.getText().toString());
			if(X1.getText().toString().equals("")){
				x1 = 0f;
			}else{
				x1= Float.valueOf(X1.getText().toString());
				if(Tol.getText().toString().equals("")){
					tol= 0f;
				}else{
					tol= Float.valueOf(Tol.getText().toString());
				}
			}
		}


		Calcularcito.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double res= 0.0;
				//obtenemos cada valor de los campos.
				if(!itera.getText().toString().equals("")){
					iter = Integer.parseInt(itera.getText().toString());

				}
				if((!X1.getText().toString().equals(""))){
					xuST = Double.parseDouble(X1.getText().toString());
				}

				if((!Tol.getText().toString().equals(""))){
					tolsST = Double.parseDouble(Tol.getText().toString());
				}
				
				if((!X0.getText().toString().equals(""))){
					xcST = Double.parseDouble(X0.getText().toString());
				}
				
				
				
				try {
					e.setFunction(g.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//implemento el metodo punto fijo
				double fx0 = e.calculate(xcST);
				if(fx0 == 0){
					resultadoST.setText(String.valueOf(xcST));
				}else{
					double fx1 = e.calculate(xuST);
					int cont =0;
					double error = tolsST + 1;
					double den = fx1 - fx0;
					double x2;
					while(error > tolsST &&  fx1 != 0 && den != 0 && cont < iter){
						x2 = xuST -(fx1*(xuST-xcST)/den);
						error = ((Math.abs(x2 - xuST))/x2)*100;
						xcST = xuST;
						fx0 = e.calculate(xuST);
						xuST = x2;
						fx1 = e.calculate(xuST);
						den = fx1 - fx0;
						cont++;
					}
					if(fx1 == 0){
						resultadoST.setText(String.valueOf(xuST));
					}else{
						if(error < tolsST){
							resultadoST.setText(String.valueOf(xuST));
						}else{
							if(den == 0){
								resultadoST.setText("Hay una posible raiz multiple");
							}else{
								resultadoST.setText("no es suficiente con "+ iter +" iteraciones");
							}
						}
					}	
				}
			}
		});

	}

}
