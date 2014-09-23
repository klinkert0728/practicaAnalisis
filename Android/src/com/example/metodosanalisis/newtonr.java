package com.example.metodosanalisis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newtonr extends Activity {
	EditText Xr, Tol, funcionf, funciong, resultado;
	Float x0,x1, tol;
	evaluadorFunciones ef = new evaluadorFunciones();
	evaluadorFunciones eg = new evaluadorFunciones();
	double xr,tolsnr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newtonr);

		//obtenemos la variables de los campos
		Xr = (EditText) findViewById (R.id.Xrn);
		Tol =(EditText) findViewById (R.id.tolnr);
		funcionf = (EditText) findViewById (R.id.funcionf);
		funciong = (EditText) findViewById (R.id.funciong);
		resultado =(EditText) findViewById (R.id.solucionnr);
		//para que al acceder a los campos de texto no de error si estan vacíos.
		if (Xr.getText().toString().equals("")) {
			x0 = 0f;
		} else{
			x0= Float.valueOf(Xr.getText().toString());

			if(Tol.getText().toString().equals("")){
				tol= 0f;
			}else	{
				tolsnr= Float.valueOf(Tol.getText().toString());	
			}

		}

		//función del boton calcular.
		Button calcular = (Button) findViewById(R.id.calcularnr);

		calcular.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				double eRelativo= tolsnr + 1;//es solo para que en la primera iteracion sea diferente de 0;
				double x =0;
				boolean piteracion = true;
				double resf=0.0;
				double resg =0.0;
				//obtenemos cada valor de los campos.
				if(!Xr.getText().toString().equals("")){
					xr = Double.parseDouble(Xr.getText().toString());
					x=xr;
				}

				if((!Tol.getText().toString().equals(""))){
					tolsnr = Double.parseDouble(Tol.getText().toString());
				}



				while(eRelativo!=0){
					if(eRelativo <tolsnr){
						break;
					}
					//System.out.println(x);
					//System.out.println(eRelativo+"relativo"+">"+tolsnr);

					try {

						ef.setFunction(funcionf.getText().toString());
						//eg.setFunction(funciong.getText().toString());
						resf = ef.calculate(x);
						ef.setFunction(funciong.getText().toString());
						resg = ef.calculate(x);
						double temp1=x;				  
						x= temp1-(resf/resg);
						eRelativo = (Math.abs(x-temp1)/x)*100;
						if(piteracion ==false){
							eRelativo = (Math.abs(x-temp1)/x)*100;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(resf);
					System.out.println(resg);
				}
				//implementamos el método de bisección

				//resultado.setText(String.valueOf(x));
				resultado.setText(String.valueOf(x));
			}

		});

	}
}
