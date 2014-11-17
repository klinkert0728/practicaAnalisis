package com.example.metodosanalisis;

import java.util.Arrays;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SetUpIterativeMethods extends Activity {

	public static double[][] matrix;
	public static double[] b;
	double[] inicialValues;
	int method;
	TextView resultText;

	TableLayout tableView;
	int sizeOfMatrix;
	EditText[] editVector;

	EditText valorInicial;
	EditText iteraciones;
	EditText tolerancia;
	EditText alpha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_up_iterative_methods);
		iteraciones = (EditText) findViewById(R.id.iteraciones);
		tolerancia = (EditText) findViewById(R.id.tolerancia);
		method = this.getIntent().getExtras().getInt("nextMethod");
		alpha = (EditText) findViewById(R.id.alpha);
		resultText=(TextView)findViewById(R.id.resultado);
		editVector = new EditText[matrix.length];
		tableView = (TableLayout) findViewById(R.id.tableLayout);

		// sizeOfMatrix = matrix.length;
		this.fillVector(matrix.length, tableView);

	}

	public double[] getValuesOfVector() {

		double[] vector = new double[matrix.length];
		for (int i = 0; i < matrix.length; i++) {

			vector[i] = Double.parseDouble(editVector[i].getText().toString());

		}

		return vector;

	}

	private void fillVector(final int n, TableLayout table) {
		table.removeAllViews();
		TableRow row = new TableRow(this);
		row.setLayoutParams(new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT));
		for (int i = 0; i < n; i++) {
			editVector[i] = new EditText(this);

			editVector[i].setInputType(InputType.TYPE_CLASS_NUMBER
					| InputType.TYPE_NUMBER_FLAG_DECIMAL
					| InputType.TYPE_NUMBER_FLAG_SIGNED);
			editVector[i].setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			// edit.setText(Double.toString(editBArray[i]));
			editVector[i].setHint("X" + i);

			row.addView(editVector[i]);
		}
		table.addView(row);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_up_iterative_methods, menu);
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

	public void calcular(View v) {
		inicialValues = this.getValuesOfVector();

		double tol = Double.parseDouble(tolerancia.getText().toString());

		int numberOfIteration = Integer.parseInt(iteraciones.getText()
				.toString());

		double realAlpha = Double.parseDouble(alpha.getText().toString());
		;

		switch (method) {
		case 1:
			this.gaussSeidel(matrix, b, matrix.length, inicialValues,
					numberOfIteration, tol, realAlpha);

			break;
		case 2:
			this.jacobi(matrix, b, matrix.length, inicialValues,
					numberOfIteration, tol, realAlpha);
			break;
		default:
			break;
		}
		
	}

	public void jacobi(double[][] matrix, double[] b, int n, double[] x0,
			int iteraciones, double tolerancia, double alpha) {
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
			resultText.setText("Vector X  "+Arrays.toString(x));
			System.out.println(Arrays.toString(x));
			System.out.println("es una aproximación con una tolerancia de "
					+ tolerancia);
		} else {
			System.out.println("Fracaso en " + iteraciones + " iteraciones.");
		}
	}

	public double norma(double[] x, double[] x0, int n) {
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

	public void imprimirMarcas(int n) {
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

	public void imprimirVector(double[] vector, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(vector[i]);
			printSpaces(String.valueOf(vector[i]).length(), 30);
		}
	}

	public void printSpaces(int n, int k) {
		if (n < k) {
			for (int i = 0; i < k - n; i++) {
				System.out.print(" ");
			}
		}
	}

	// Se implementa GaussSeidel

	public void gaussSeidel(double[][] matrix, double[] b, int n, double[] x0,
			int iteraciones, double tolerancia, double alpha) {
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
			resultText.setText("Vector X"+Arrays.toString(x));
			System.out.println("es una aproximación con una tolerancia de "
					+ tolerancia);
		} else {
			System.out.println("Fracaso en " + iteraciones + " iteraciones.");
		}
	}

}
