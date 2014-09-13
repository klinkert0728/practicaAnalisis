package com.example.metodosanalisis;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
public class biseccion extends Activity {
	EditText Xcero, Xuno,Tol;
	float x0,x1,tol;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biseccion);
		//obtenemos la variables de los campos
		Xcero = (EditText) findViewById (R.id.X0);
		Xuno =  (EditText) findViewById (R.id.X1);
		Tol =   (EditText) findViewById (R.id.tol);
		
		//pasamos las variables de strings a numeros. se daña si se pone no c por que
		//x0= Float.valueOf(Xcero.getText().toString());
		/*x1= Double.valueOf(Xuno.getText().toString());
		tol= Double.valueOf(Tol.getText().toString());
		
		System.out.println("x0= "+ x0 + " x1= "+ x1 +" tol= " +tol);
	**/	
	}
 
}
