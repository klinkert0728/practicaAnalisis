package com.example.metodosanalisis;
import android.os.Bundle;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class biseccion extends Activity {
	EditText Xcero, Xuno,Tol, funcion;
	Float x0,x1, tol;
	evaluadorFunciones e = new evaluadorFunciones();
	String fun1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biseccion);
		//obtenemos la variables de los campos
		Xcero = (EditText) findViewById (R.id.X0);
		Xuno =  (EditText) findViewById (R.id.X1);
		Tol =   (EditText) findViewById (R.id.tol);
		funcion = (EditText) findViewById (R.id.funcion);
		String fun = funcion.getText().toString();
		
		//pasamos las variables de strings a numeros. se da�a si se pone no c por que
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
		Button calcular = (Button) findViewById(R.id.calcular);

		calcular.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	fun1 = e.setFunction(fun);
		    	System.out.println("x0= "+ x0 + " x1= "+ x1 +" tol= " +tol);
		    }
		});
			

					

			}
 
}
