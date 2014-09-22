package com.example.metodosanalisis;
import android.os.Bundle;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.util.Log;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
public class biseccion extends Activity {
	EditText Xcero, Xuno,Tol, funcion, resultado;
	Float x0,x1, tol;
	evaluadorFunciones e = new evaluadorFunciones();
	double xc, xu,tols;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biseccion);
		
		
		//obtenemos la variables de los campos
		Xcero = (EditText) findViewById (R.id.X0);
		Xuno =  (EditText) findViewById (R.id.X1);
		Tol =   (EditText) findViewById (R.id.tol);
		funcion = (EditText) findViewById (R.id.funcion);
		resultado = (EditText) findViewById (R.id.resultado);


		//para que al acceder a los campos de texto no de error si estan vacíos.
		if (Xcero.getText().toString().equals("")) {
			x0 = 0f;
		} else{
			x0= Float.valueOf(Xcero.getText().toString());
			if(Xuno.getText().toString().equals("")){
				x1 = 0f;
			}else	{
				x1= Float.valueOf(Xuno.getText().toString());
				if(Tol.getText().toString().equals("")){
					tol= 0f;
				}else	{
					tol= Float.valueOf(Tol.getText().toString());

				}
			}

		}
		
		
		//función del boton calcular.
		Button calcular = (Button) findViewById(R.id.calcular);

		calcular.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				double res= 0.0;
				//obtenemos cada valor de los campos.
				if(!Xcero.getText().toString().equals("")){
					xc = Double.parseDouble(Xcero.getText().toString());

				}
				if((!Xuno.getText().toString().equals(""))){
					xu = Double.parseDouble(Xuno.getText().toString());
				}

				if((!Tol.getText().toString().equals(""))){
					tols = Double.parseDouble(Tol.getText().toString());
				}
				
				double temp1=xc;
				double eRelativo=2;//es solo para que en la primera iteracion sea diferente de 0;
				double x =0;
				boolean piteracion = true;
				
				try {

					e.setFunction(funcion.getText().toString());
					// res = e.calculate(Double.parseDouble(Xcero.getText().toString()));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//implementamos el método de bisección
				x=xc;
				int i =0;
				while(eRelativo!=0){
					if (eRelativo < tols ){
						break;
					}
					System.out.println(x);
					System.out.println(eRelativo+"relativo"+">"+tols);
					x= (xc+xu)/2;
					if((e.calculate(xc)*e.calculate(x))<0){
						xu=x;	
					}else{
						xc=x;
					}
					if(piteracion ==false){
						eRelativo = (Math.abs(x-temp1)/x)*100;
						temp1 =x;
					}
					piteracion=false;
					temp1=x;
				}
				//resultado.setText(String.valueOf(x));
				resultado.setText(String.valueOf(x));
			}

		});




	}

}
