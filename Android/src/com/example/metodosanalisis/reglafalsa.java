package com.example.metodosanalisis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class reglafalsa extends Activity  {
	EditText Xcerorf, Xunorf, funcionrf, resultadorf, Tolrf;
	Float x0rf,x1rf,tolrf;
	double xcrf, xurf, tolsrf;
	evaluadorFunciones e = new evaluadorFunciones();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reglafalsa);
		
		//obtenemos la variables de los campos
				Xcerorf = (EditText) findViewById (R.id.X0rf);
				Xunorf =  (EditText) findViewById (R.id.X1rf);
				Tolrf =   (EditText) findViewById (R.id.tolrf);
				funcionrf = (EditText) findViewById (R.id.funcionrf);
				resultadorf = (EditText) findViewById (R.id.resultadorf);
				//para que al acceder a los campos de texto no de error si estan vacíos.
				if (Xcerorf.getText().toString().equals("")) {
					x0rf = 0f;
				} else{
					x0rf= Float.valueOf(Xcerorf.getText().toString());
					if(Xunorf.getText().toString().equals("")){
						x1rf = 0f;
					}else	{
						x1rf= Float.valueOf(Xunorf.getText().toString());
						if(Tolrf.getText().toString().equals("")){
							tolrf= 0f;
						}else	{
							tolrf= Float.valueOf(Tolrf.getText().toString());

						}
					}

				}
		//funcion del boton calcular
				
				Button calcularrf = (Button) findViewById(R.id.calcularrf);
				calcularrf.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						double res= 0.0;
						//obtenemos cada valor de los campos.
						if(!Xcerorf.getText().toString().equals("")){
							xcrf = Double.parseDouble(Xcerorf.getText().toString());

					}
						if((!Xunorf.getText().toString().equals(""))){
							xurf = Double.parseDouble(Xunorf.getText().toString());
						}

						if((!Tolrf.getText().toString().equals(""))){
							tolsrf = Double.parseDouble(Tolrf.getText().toString());
						}
						
						double temp1=xcrf;
						double eRelativo= tolsrf + 1;//es solo para que en la primera iteracion sea diferente de 0;
						double xrf =0;
						boolean piteracion = true;
						
						try {

							e.setFunction(funcionrf.getText().toString());
							 //res = e.calculate(Double.parseDouble(Xcerorf.getText().toString()));

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//implementamos el método de puntofijo
						xrf=xcrf;
						int i =0;
						while(eRelativo >= tolsrf){
							System.out.println(xrf);
							System.out.println(eRelativo+"relativo"+">"+tolsrf);
							double fb = e.calculate(xurf);
							double fa = e.calculate(xcrf);
							temp1 = xrf;
							
							xrf= xurf-((fb*(xcrf-xurf))/(fa-fb));
							
							if((e.calculate(xcrf)*e.calculate(xrf))<0){
								xurf=xrf;	
							}else{
								xcrf=xrf;
							}
							eRelativo = (Math.abs(xrf-temp1)/xrf)*100;
							
							/*if(piteracion ==false){
								eRelativo = (Math.abs(xrf-temp1)/xrf)*100;
								temp1 =xrf;
							}*/
							//piteracion=false;
							//temp1=xrf;
						}
						//resultado.setText(String.valueOf(x));
						resultadorf.setText(String.valueOf(xrf));
					}

				});
	}
}
