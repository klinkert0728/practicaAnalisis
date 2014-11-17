package com.example.metodosanalisis;

import java.lang.reflect.Method;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SolucionIntegracion extends Activity {
	int iterations;
	String formula;
	String derivada;
	int limSup;
	int limInf;
	int method;
	TextView resultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solucion_integracion);

		iterations = this.getIntent().getExtras().getInt("particiones");
		formula = this.getIntent().getExtras().getString("Formula");
		try{
			derivada = this.getIntent().getExtras().getString("Derivada");
		}catch (Exception e){
			System.out.println("No hay derivada");
		}
		
		limInf = this.getIntent().getExtras().getInt("LimInf");
		limSup = this.getIntent().getExtras().getInt("LimSup");
		resultado = (TextView) findViewById(R.id.resultado);
		method	= this.getIntent().getExtras().getInt("method");


		// IntegracionNumerica.simpson13Generalizado(formula,a,b,nroParticiones);
		try {
			
			switch (method) {
			case 1:
				this.simpson13Generalizado(formula, limInf, limSup, iterations);
				break;
			case 2:
				this.simpson38Generalizado(formula, limInf, limSup, iterations);
				break;
			case 3:
				this.integrarTrapecioGeneralizado(formula, derivada, limInf,
						limSup, iterations, 0);
				break;
			default:
				break;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.integrarTrapecioGeneralizado(formula,"exp(x)",a,b,nroParticiones,0);
	}


	public void integrarTrapecioGeneralizado(String funcion,
			String segundaDerivada, double a, double b, int numeroIntervalos,
			double zi) throws Exception {
		System.out.println("Integración trapecio con " + numeroIntervalos
				+ " particiones.");
		System.out.println("\nFunción: " + funcion + " entre [" + a + "," + b
				+ "].\n");
		double h = (b - a) / numeroIntervalos;
		double sum = functionX(a, funcion) + functionX(b, funcion);

		String formula = "h/2" + "[ f(" + a + ") + 2*(";
		String formula2 = (h / 2) + "[" + functionX(a, funcion) + " + 2*(";

		double temp = 0;
		imprimirMarcas();
		imprimirDato(0, a, functionX(a, funcion));
		for (int i = 1; i < numeroIntervalos; i++) {
			temp = temp + functionX(a + i * h, funcion);
			imprimirDato(i, a + i * h, functionX(a + i * h, funcion));
			formula += "f(" + (a + i * h) + ")";
			formula2 += functionX(a + i * h, funcion);
			if (i < numeroIntervalos - 1) {
				formula += " + ";
				formula2 += " + ";
			}
		}
		sum += 2 * temp;
		imprimirDato(numeroIntervalos, b, functionX(b, funcion));
		formula += ") + f(" + b + ") ]";
		formula2 += ") + " + functionX(b, funcion) + "]";
		System.out.println("\nFormula: ");
		System.out.println(formula);
		System.out.println(formula2);
		double resultado = (h / 2) * sum;
		double error = ((Math.pow(b - a, 3))
				/ (12 * (Math.pow(numeroIntervalos, 2))) * functionX(zi,
				segundaDerivada));
		System.out.println("\nResultado:");
		System.out.println("Integral de " + a + " hasta " + b + " de "
				+ funcion + " es " + resultado + " con un error de " + error);
		this.resultado.setText("Integral de " + a + " hasta " + b + " de "
				+ funcion + " es " + resultado + " con un error de " + error);

		// Regla trapecio simple
		// Integral de a hasta b de p(x) = (b-a)(f(a)+f(b))/2 +
		// (f''(z)*(b-a)^3)/12

		// Regla trapecio general
		// Integral de x0 a xn de f(x) = (h/2)[f(x0)+ 2*(sumatoria de f(xi) i=1
		// hasta n-1) + f(xn)] + (b-a)^3*f''(z)/12n^2
	}

	public void simpson13Generalizado(String funcion, double a, double b,
			int numeroIntervalos) throws Exception {
		if ((numeroIntervalos % 2) != 0) {
			this.resultado.setText("El número de intervalos debe ser un número par.");
		} else {
			System.out.println("Integración 1/3 generalizado con "
					+ numeroIntervalos + " particiones.");
			System.out.println("\nFunción: " + funcion + " entre [" + a + ","
					+ b + "].\n");
			double h = (b - a) / numeroIntervalos;
			double resultado = functionX(a, funcion);
			double sum1 = 0;
			double sum2 = 0;
			imprimirMarcas();
			imprimirDato(0, a, functionX(a, funcion));
			for (int i = 1; i < numeroIntervalos; i++) {
				if ((i % 2) == 0) {
					sum1 += functionX((a + i * h), funcion);
					imprimirDato(i, a + i * h, functionX(a + i * h, funcion));
				} else {
					if ((i % 2) == 1) {
						sum2 += functionX((a + i * h), funcion);
						imprimirDato(i, a + i * h,
								functionX(a + i * h, funcion));
					}
				}
			}
			imprimirDato(numeroIntervalos, b, functionX(b, funcion));
			resultado += 2 * sum1 + 4 * sum2 + functionX(b, funcion);
			resultado = (h / 3) * resultado;
			System.out.println("\nResultado:");
			this.resultado.setText("Integral de " + a + " hasta " + b + " de "
					+ funcion + " es " + resultado);
		}

		// Regla simpson 1/3 simple
		// Integral de x0 a x2 de f(x) = (h/3)[f(x0)+ 4*f(x1) + f(x2)] - 1/90
		// (h)^5*f''''(z)

		// Regla simpson 1/3 generalizado (numero de puntos impar osea numero de
		// intervalos par)
		// Integral de x0 a xn de f(x) = (h/3)[f(x0)+ 4*(sumatoria de f(xi)
		// impares) + 2*(sumatoria de f(xi) pares) + f(xn)] - 1/(180*n^4)
		// (h)^5*f''''(z)

	}

	public void simpson38Generalizado(String funcion, double a, double b,
			int numeroIntervalos) throws Exception {

		if ((numeroIntervalos % 3) != 0) {
			this.resultado.setText("El numero de intervalos debe ser mpultiplo de 3.");
		} else {
			System.out.println("Integración 3/8 generalizado con "
					+ numeroIntervalos + " particiones.");
			System.out.println("\nFunción: " + funcion + " entre [" + a + ","
					+ b + "].\n");

			double h = (b - a) / numeroIntervalos;
			double resultado = functionX(a, funcion);
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;

			imprimirMarcas();
			imprimirDato(0, a, functionX(a, funcion));
			for (int i = 1; i < numeroIntervalos; i++) {
				if ((i % 3) == 1) {
					sum1 += functionX((a + i * h), funcion);
					imprimirDato(i, a + i * h, functionX(a + i * h, funcion));
				} else {
					if ((i % 3) == 2) {
						sum2 += functionX((a + i * h), funcion);
						imprimirDato(i, a + i * h,
								functionX(a + i * h, funcion));
					} else {
						sum3 += functionX((a + i * h), funcion);
						imprimirDato(i, a + i * h,
								functionX(a + i * h, funcion));
					}
				}
			}
			imprimirDato(numeroIntervalos, b, functionX(b, funcion));
			resultado += 3 * sum1;
			resultado += 3 * sum2;
			resultado += 2 * sum3;
			resultado += functionX(b, funcion);
			resultado = (3 * h / 8) * resultado;
			System.out.println("\nResultado:");
			System.out.println("Integral de " + a + " hasta " + b + " de "
					+ funcion + " es " + resultado);

			// Regla simpson 3/8 simple (número de intervalos debe ser múltiplo
			// de 3)
			// Integral de x0 a x3 de f(x) = (3h/8)[f(x0)+ 3*f(x1) + 3*f(x2) +
			// f(x3)] - 1/6480 (h)^5*f''''(z)
		}
	}

	private void imprimirMarcas() {
		System.out.print("n");
		printSpaces(String.valueOf("n").length(), 30);

		System.out.print("x");
		printSpaces(String.valueOf("x").length(), 30);

		System.out.print("f(x)");
		printSpaces(String.valueOf("f(x)").length(), 30);

		System.out.println("");
	}

	private void imprimirDato(int n, double a, double fa) {

		System.out.print(n);
		printSpaces(String.valueOf(n).length(), 30);

		System.out.print(a);
		printSpaces(String.valueOf(a).length(), 30);

		System.out.print(fa);
		printSpaces(String.valueOf(fa).length(), 30);

		System.out.println("");
	}

	private double functionX(double x, String function) throws Exception {
		Calculable calc;
		try {
			calc = new ExpressionBuilder(function).withVariable("x", x).build();
			double result = calc.calculate();
			return result;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	private void printSpaces(int n, int k) {
		if (n < k) {
			for (int i = 0; i < k - n; i++) {
				System.out.print(" ");
			}
		}
	}

}
