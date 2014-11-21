package com.example.metodosanalisis;


import org.w3c.dom.Text;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SolucionInterpolacion extends Activity {
	
	public static double[]vectorX;
	public static double[]vectorY;
	int method;
	int numberOfpoints;
	double valueToEvaluate;
	TextView sol;
	TextView func;
	TextView solFinal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solucion_interpolacion);
		method = this.getIntent().getExtras().getInt("method");
		numberOfpoints = this.getIntent().getExtras().getInt("numberOfPoints");
		valueToEvaluate = this.getIntent().getExtras().getDouble("valueToEvaluate");
		sol = (TextView) findViewById(R.id.polinomio);
		func = (TextView)findViewById(R.id.funcion);
		
		switch (method) {
		case 1:
			
			this.interpolacionNewtonDiferenciasDivididas(numberOfpoints, valueToEvaluate,vectorX, vectorY);
			break;
		case 2:
		
			this.interpolacionLagrange(numberOfpoints, valueToEvaluate, vectorX, vectorY);
			break;
		case 3:
			
			this.interpolacionNeville(numberOfpoints, valueToEvaluate,vectorX, vectorY);
			break;
		default:
			break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solucion_interpolacion, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	// Newton
	public  void interpolacionNewtonDiferenciasDivididas(int nroPuntos, double valor, double[] x, double[] y){  
        System.out.println("Newton con diferencias divididas.");      
        double [][] tabla = new double[nroPuntos][nroPuntos];       
        for(int i = 0; i<nroPuntos;i++){
            tabla[i][0] = y[i];
        }    
        
        for(int i = 0; i<nroPuntos;i++){
            for(int j = 1; j<i+1;j++){
                tabla[i][j] = (tabla[i][j-1] - tabla[i-1][j-1])/(x[i] - x[i-j]);
                
            }
        }
        System.out.println("\nTabla de datos:\n");
        imprimirMatriz(tabla, nroPuntos,x);
        System.out.println("Polinomio interpolante:");
        String pol = "P(x): "+String.valueOf(tabla[0][0]);
        String temp = "";
        double resultado = tabla[0][0];
        double aux = 1;
        for(int i = 1; i<nroPuntos;i++){
            temp = temp + "(x"+"-"+(x[i-1])+")";
            pol = pol + "\n"+(tabla[i][i]>0?"+":"")+(tabla[i][i]+"*"+temp);
            
            aux = aux * (valor-x[i-1]);
            resultado = resultado + tabla[i][i]*aux;
        }
        System.out.println(pol);
        sol.setText(pol);
        System.out.println("\nResultado:");
        System.out.println("f("+valor+") = "+ resultado);
        func.setText("f("+valor+") = "+ resultado);
    }

    private  void imprimirMatriz(double [][] matrix, int n, double x[]){
        System.out.print("Xi");
        printSpaces(String.valueOf("Xi").length(),30);
        for(int i=0;i<n;i++){
            System.out.print("f"+(i)+"[]");
            printSpaces(String.valueOf("f"+i+"[]").length(),30);
        }
        System.out.println("");
        for(int i=0; i< n;i++){
            System.out.print(x[i]);
            printSpaces(String.valueOf(x[i]).length(),30);
            for(int j=0; j <n; j++){
                System.out.print(matrix[i][j]);
                printSpaces(String.valueOf(matrix[i][j]).length(),30);
            }
            System.out.print("\n");
        }
        System.out.println("");
    }
     
    private  void printSpaces(int n, int k){
        if(n<k){
            for(int i = 0; i<k-n;i++){
                System.out.print(" ");
            }
        }
    }
    
    private  String strI(int a, int b){
        String str = "";
        for(int i=a;i<=b;i++){
           str = str +""+ i;
        }
        return str;
    }
    // Lagrange
    public  void interpolacionLagrange(int nroPuntos, double valor, double[] x, double[] y){
        System.out.println("Lagrange.");
        System.out.println("\nConstrucción función L(x):");
        double resultado = 0;
        String pol = "P(x): ";  
        for(int k = 0; k<nroPuntos;k++){
            double productoria = 1;
            String termino = "";
            for(int i = 0; i < nroPuntos ; i++){
                if(i!=k){
                    productoria = productoria * (valor-x[i])/(x[k]-x[i]);
                    termino = termino + ("[(x-"+x[i]+")/("+x[k]+"-"+x[i]+")]");
                }
            }
            System.out.println("L"+k+"(x):"+termino);
            pol += (y[k]>0?"+":"")+y[k]+"*"+termino+"\n";
            resultado += productoria * y[k];
        } 
        System.out.println("\nPolinomio interpolante:");
        System.out.println(pol); 
        System.out.println("Resultado:");
        System.out.println("f("+valor+") = "+ resultado);
        sol.setText(pol);
        func.setText("f("+valor+") = "+ resultado);
        
    }
	
    //Neville
    public  void interpolacionNeville(int nroPuntos, double valor, double[] x, double[] y){  
        System.out.println("Neville.");      
        double [][] tabla = new double[nroPuntos][nroPuntos];       
        for(int i = 0; i<nroPuntos;i++){
            tabla[i][0] = y[i];
        }    
        System.out.println("\nTabla de datos:\n");
        for(int i = 0; i<nroPuntos;i++){
            for(int j = 1; j<i+1;j++){
                tabla[i][j] = ((valor-x[i-j])*tabla[i][j-1] - ((valor-x[i])*tabla[i-1][j-1]))/(x[i] - x[i-j]);
            }
        }
        imprimirMatriz(tabla, nroPuntos,x);
        System.out.println("Resultado:");
        func.setText("f("+valor+") = "+ tabla[nroPuntos-1][nroPuntos-1]);
        System.out.println("f("+valor+") = "+ tabla[nroPuntos-1][nroPuntos-1]);
    }
    
    
}
