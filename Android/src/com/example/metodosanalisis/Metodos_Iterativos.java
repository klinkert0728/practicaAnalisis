package com.example.metodosanalisis;

import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Metodos_Iterativos extends Activity {
	CheckBox jacobi;
	CheckBox gaussSeidel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metodos__iterativos);
		jacobi = (CheckBox) findViewById(R.id.Jacobi);
		gaussSeidel = (CheckBox) findViewById(R.id.GaussSeidel);
	}

	public void calcular(View v) {

		if (jacobi.isChecked() && !gaussSeidel.isChecked()) {

			Log.i("Jacobi", "Jacobi");

		} else if (gaussSeidel.isChecked() && !jacobi.isChecked()) {
			Log.i("GaussSeidel", "GausSeidel");

		} else {

			Log.i("Nada", "Nada");
		}

	}

	// Se implementa Jacobbi

	public static void jacobi(double[][] matrix, double[] b, int n,
			double[] x0, int iteraciones, double tolerancia, double alpha) {
		int contador = 1;
		imprimirMarcas(n);

		System.out.print("\n" + (contador - 1));
		printSpaces(String.valueOf(contador - 1).length(), 15);
		System.out.print("");
		imprimirVector(x0, n);
		System.out.println("");
		double error = tolerancia + 1;
		double[] x = new double[n];
		while (error > tolerancia && contador < iteraciones) {
			error = 0;
			for (int i = 1; i < n + 1; i++) {
				double suma = 0;
				for (int j = 1; j < n + 1; j++) {
					if (i != j) {
						suma = suma + matrix[i - 1][j - 1] * x0[j - 1];
					}
				}
				x[i - 1] = (b[i - 1] - suma) / matrix[i - 1][i - 1];
				x[i - 1] = alpha * (x[i - 1]) + (1 - alpha) * (x0[i - 1]);
			}
			error = norma(x, x0, n);
			for (int i = 1; i < n + 1; i++) {
				x0[i - 1] = x[i - 1];
			}
			contador++;
			System.out.print((contador - 1));
			printSpaces(String.valueOf(contador - 1).length(), 15);
			imprimirVector(x, n);
			System.out.print(error + "\n");
		}

		if (error < tolerancia) {

			System.out.println("\nVector X");
			System.out.println(Arrays.toString(x));
			System.out.println("es una aproximación con una tolerancia de "
					+ tolerancia);
		} else {
			System.out.println("Fracaso en " + iteraciones + " iteraciones.");
		}
	}

	public static double norma(double[] x, double[] x0, int n) {
		double mayor = Double.NEGATIVE_INFINITY;
		double norma = 0;

		for (int i = 1; i < n; i++) {
			if (Math.abs(x[i - 1] - x0[i - 1]) > mayor) {
				mayor = Math.abs(x[i - 1] - x0[i - 1]);
			}
		}
		norma = mayor;
		return norma;
	}

	public static void imprimirMarcas(int n) {
		System.out.print("Iteraciones");
		printSpaces(String.valueOf("Iteraciones").length(), 15);
		int[] marcas = new int[n];
		for (int i = 0; i < n; i++) {
			marcas[i] = i + 1;
		}
		for (int i = 0; i < n; i++) {
			System.out.print("X" + marcas[i]);
			printSpaces(String.valueOf(marcas[i]).length() + 1, 30);
		}
		System.out.print("Error");
	}

	public static void imprimirVector(double[] vector, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(vector[i]);
			printSpaces(String.valueOf(vector[i]).length(), 30);
		}
	}

	public static void printSpaces(int n, int k) {
		if (n < k) {
			for (int i = 0; i < k - n; i++) {
				System.out.print(" ");
			}
		}
	}

	// Se implementa GaussSeidel

	public static void gaussSeidel(double[][] matrix, double[] b, int n,
			double[] x0, int iteraciones, double tolerancia, double alpha) {
		int contador = 1;
		imprimirMarcas(n);

		System.out.print("\n" + (contador - 1));
		printSpaces(String.valueOf(contador - 1).length(), 15);
		System.out.print("");
		imprimirVector(x0, n);
		System.out.println("");
		double error = tolerancia + 1;
		double[] x = new double[n];
		while (error > tolerancia && contador < iteraciones) {
			error = 0;
			for (int i = 1; i < n + 1; i++) {
				x[i - 1] = x0[i - 1];
			}
			for (int i = 1; i < n + 1; i++) {
				double suma = 0;
				for (int j = 1; j < n + 1; j++) {
					if (i != j) {
						suma = suma + matrix[i - 1][j - 1] * x[j - 1];
					}
				}
				x[i - 1] = (b[i - 1] - suma) / matrix[i - 1][i - 1];
				x[i - 1] = alpha * (x[i - 1]) + (1 - alpha) * (x0[i - 1]);
			}
			error = norma(x, x0, n);
			for (int i = 1; i < n + 1; i++) {
				x0[i - 1] = x[i - 1];
			}
			contador++;
			System.out.print((contador - 1));
			printSpaces(String.valueOf(contador - 1).length(), 15);
			imprimirVector(x, n);
			System.out.print(error + "\n");
		}

		if (error < tolerancia) {

			System.out.println("\nVector X");
			System.out.println(Arrays.toString(x));
			System.out.println("es una aproximación con una tolerancia de "
					+ tolerancia);
		} else {
			System.out.println("Fracaso en " + iteraciones + " iteraciones.");
		}
	}
}