package com.example.metodosanalisis;

import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Factorizacion extends Activity {
	CheckBox crout;
	CheckBox doolittle;
	CheckBox cholesky;
	TextView Resultado;

	int sizeOfMatrix;
	TableLayout table;
	TableLayout b;
	EditText[][] editMatrix;
	EditText[] editBArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_factorizacion);
		crout = (CheckBox) findViewById(R.id.Crout);
		doolittle = (CheckBox) findViewById(R.id.Doolittle);
		cholesky = (CheckBox) findViewById(R.id.Cholesky);
		Resultado = (TextView) findViewById(R.id.resultado);
		
		sizeOfMatrix = getIntent().getExtras().getInt("size");

		editMatrix = new EditText[sizeOfMatrix][sizeOfMatrix];

		editBArray = new EditText[sizeOfMatrix];

		table = (TableLayout) findViewById(R.id.tableLayout);

		b = (TableLayout) findViewById(R.id.vectorLayout);
		this.fillTable(sizeOfMatrix, table);
		this.fillVector(sizeOfMatrix, b);

	}

	private void fillVector(final int n, TableLayout table) {
		table.removeAllViews();
		TableRow row = new TableRow(this);
		row.setLayoutParams(new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT));
		for (int i = 0; i < n; i++) {
			editBArray[i] = new EditText(this);

			editBArray[i].setInputType(InputType.TYPE_CLASS_NUMBER
					| InputType.TYPE_NUMBER_FLAG_DECIMAL
					| InputType.TYPE_NUMBER_FLAG_SIGNED);
			editBArray[i].setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			// edit.setText(Double.toString(editBArray[i]));
			editBArray[i].setHint("B" + i);

			row.addView(editBArray[i]);
		}
		table.addView(row);

	}

	private void fillTable(final int n, TableLayout table) {
		table.removeAllViews();
		for (int i = 0; i < n; i++) {
			TableRow row = new TableRow(this);
			row.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			for (int j = 0; j < n; j++) {
				editMatrix[i][j] = new EditText(this);
				editMatrix[i][j].setInputType(InputType.TYPE_CLASS_NUMBER
						| InputType.TYPE_NUMBER_FLAG_DECIMAL
						| InputType.TYPE_NUMBER_FLAG_SIGNED);
				editMatrix[i][j].setLayoutParams(new TableRow.LayoutParams(
						TableRow.LayoutParams.WRAP_CONTENT,
						TableRow.LayoutParams.WRAP_CONTENT));

				// edit.setText(Double.toString(matrix[i][j]));
				editMatrix[i][j].setHint("M" + i + j);
				editMatrix[i][j].setEnabled(true);
				row.addView(editMatrix[i][j]);
			}
			table.addView(row);
		}
	}

	public double[][] getValuesOfMatrix() {
		double[][] matrix = new double[sizeOfMatrix][sizeOfMatrix];

		for (int i = 0; i < sizeOfMatrix; i++) {

			for (int j = 0; j < sizeOfMatrix; j++) {

				matrix[i][j] = Double.parseDouble(editMatrix[i][j].getText()
						.toString());
			}
		}

		return matrix;

	}

	public double[] getValuesOfVector() {

		double[] vector = new double[sizeOfMatrix];
		for (int i = 0; i < sizeOfMatrix; i++) {

			vector[i] = Double.parseDouble(editBArray[i].getText().toString());

		}

		return vector;

	}

	public void calcular(View v) {
		// cambiar matriz
		double[][] matrix = this.getValuesOfMatrix();
		double[] b = this.getValuesOfVector();
		double[] x0 = { 0, 0 };

		if (crout.isChecked() && !doolittle.isChecked()
				&& !cholesky.isChecked()) {
			this.factorizacionDirectaCrout(matrix, b, sizeOfMatrix);
		} else if (doolittle.isChecked() && !crout.isChecked()
				&& !cholesky.isChecked()) {
			this.factorizacionDirectaDoolittle(matrix, b, sizeOfMatrix);

		} else if (cholesky.isChecked() && !doolittle.isChecked()
				&& !crout.isChecked()) {
			this.factorizacionDirectaCholesky(matrix, b, sizeOfMatrix);
		} else {

		}

	}

	// Se implementa Crout

	public void factorizacionDirectaCrout(double A[][], double[] b, int n) {
		double[][] L = new double[n][n];
		double[][] U = new double[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j) {
					U[i][j] = Double.POSITIVE_INFINITY;
					L[i][j] = 0;
				} else if (i > j) {
					L[i][j] = Double.POSITIVE_INFINITY;
					U[i][j] = 0;
				} else if (i == j) {
					U[i][j] = 1;
					L[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}

		System.out.println("Etapa 0");
		System.out.println("Matriz A");
		imprimirMatriz(A, n);
		System.out.println("Matriz L");
		imprimirMatriz(L, n);
		System.out.println("Matriz U");
		imprimirMatriz(U, n);

		for (int k = 1; k < n + 1; k++) {
			System.out.println("Etapa " + k);
			System.out.println("Encontrar la columna " + k + " de L y la fila "
					+ k + " de U.");
			System.out.println("Matriz A");
			imprimirMatriz(A, n);
			double suma = 0;
			for (int p = 0; p < k - 1; p++) {
				suma += L[k - 1][p] * U[p][k - 1];
			}
			L[k - 1][k - 1] = (A[k - 1][k - 1] - suma) / U[k - 1][k - 1];
			for (int i = k + 1; i < n + 1; i++) {
				suma = 0;
				for (int p = 0; p < k - 1; p++) {
					suma += L[i - 1][p] * U[p][k - 1];
				}
				L[i - 1][k - 1] = (A[i - 1][k - 1] - suma) / U[k - 1][k - 1];
			}

			System.out.println("Matriz L");
			imprimirMatriz(L, n);

			for (int j = k + 1; j < n + 1; j++) {
				suma = 0;
				for (int p = 0; p < k - 1; p++) {
					suma += L[k - 1][p] * U[p][j - 1];
				}
				U[k - 1][j - 1] = (A[k - 1][j - 1] - suma) / L[k - 1][k - 1];
			}
			System.out.println("Matriz U");
			imprimirMatriz(U, n);
		}
		System.out.println("\nSustitución Progresiva Lz=b");
		double[] z = sustitucionProgresiva(L, b);
		System.out.println("Z:" + Arrays.toString(z));
		Resultado.setText("Z:" + Arrays.toString(z));
		System.out.println("\nSustitución Regresiva Ux=z");
		double[] x = sustitucionRegresiva(U, z);
		for (int i = 0; i < x.length; i++) {
			System.out.println("X" + (i + 1) + " = " + x[i]);
			Resultado.setText("X" + (i + 1) + " = " + x[i]);
		}
	}

	public double[] sustitucionProgresiva(double[][] L, double[] b) {
		int n = L.length;
		double x[] = new double[n];
		for (int i = 1; i < n + 1; i++) {
			double suma = 0;
			for (int p = i - 1; p > 0; p--) {
				double a = L[i - 2][p];
				double c = x[p - 1];
				suma += (L[i - 1][p - 1] * x[p - 1]);
			}
			x[i - 1] = (b[i - 1] - suma) / L[i - 1][i - 1];
		}
		return x;
	}

	public double[] sustitucionRegresiva(double[][] U, double[] z) {
		int n = U.length;
		double[] x = new double[n];

		for (int i = n - 1; i >= 0; i--) {
			double suma = 0;
			for (int j = i + 1; j < n; j++) {
				suma += U[i][j] * x[j];
			}
			x[i] = (z[i] - suma) / U[i][i];
		}
		return x;
	}

	public void imprimirMatriz(double[][] matrix, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j]);
				printSpaces(String.valueOf(matrix[i][j]).length(), 30);
			}
			System.out.print("\n");
		}
		System.out.println("");
	}

	public void printSpaces(int n, int k) {
		if (n < k) {
			for (int i = 0; i < k - n; i++) {
				System.out.print(" ");
			}
		}
	}

	// Se implementa doolittle
	public void factorizacionDirectaDoolittle(double A[][], double[] b, int n) {
		double[][] L = new double[n][n];
		double[][] U = new double[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j) {
					U[i][j] = Double.POSITIVE_INFINITY;
					L[i][j] = 0;
				} else if (i > j) {
					L[i][j] = Double.POSITIVE_INFINITY;
					U[i][j] = 0;
				} else if (i == j) {
					L[i][j] = 1;
					U[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		System.out.println("Etapa 0");
		System.out.println("Matriz A");
		imprimirMatriz(A, n);
		System.out.println("Matriz L");
		imprimirMatriz(L, n);
		System.out.println("Matriz U");
		imprimirMatriz(U, n);

		for (int k = 1; k < n + 1; k++) {
			System.out.println("Etapa " + k);
			System.out.println("Encontrar la columna " + k + " de L y la fila "
					+ k + " de U.");
			System.out.println("Matriz A");
			imprimirMatriz(A, n);
			double suma = 0;
			for (int p = 0; p < k - 1; p++) {
				suma += L[k - 1][p] * U[p][k - 1];
			}
			U[k - 1][k - 1] = (A[k - 1][k - 1] - suma) / L[k - 1][k - 1];

			for (int j = k + 1; j < n + 1; j++) {
				suma = 0;
				for (int p = 0; p < k - 1; p++) {

					suma += L[k - 1][p] * U[p][j - 1];
				}
				U[k - 1][j - 1] = (A[k - 1][j - 1] - suma) / L[k - 1][k - 1];
			}
			System.out.println("Matriz L");
			imprimirMatriz(L, n);
			for (int i = k + 1; i < n + 1; i++) {
				suma = 0;
				for (int p = 0; p < k - 1; p++) {
					suma += L[i - 1][p] * U[p][k - 1];
				}
				L[i - 1][k - 1] = (A[i - 1][k - 1] - suma) / U[k - 1][k - 1];
			}
			System.out.println("Matriz U");
			imprimirMatriz(U, n);
		}

		System.out.println("\nSustitución Progresiva Lz=b");
		double[] z = sustitucionProgresiva(L, b);
		System.out.println("Z:" + Arrays.toString(z));
		Resultado.setText("Z:" + Arrays.toString(z));
		System.out.println("\nSustitución Regresiva Ux=z");
		double[] x = sustitucionRegresiva(U, z);
		for (int i = 0; i < x.length; i++) {
			System.out.println("X" + (i + 1) + " = " + x[i]);
			Resultado.setText("X" + (i + 1) + " = " + x[i]);
		}
	}

	// Se implementa Cholesky
	public void factorizacionDirectaCholesky(double A[][], double[] b, int n) {
		double[][] L = new double[n][n];
		double[][] U = new double[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j) {
					U[i][j] = Double.POSITIVE_INFINITY;
					L[i][j] = 0;
				} else if (i > j) {
					L[i][j] = Double.POSITIVE_INFINITY;
					U[i][j] = 0;
				} else if (i == j) {
					L[i][j] = Double.POSITIVE_INFINITY;
					U[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}

		System.out.println("Etapa 0");
		System.out.println("Matriz A");
		imprimirMatriz(A, n);
		System.out.println("Matriz L");
		imprimirMatriz(L, n);
		System.out.println("Matriz U");
		imprimirMatriz(U, n);

		for (int k = 1; k < n + 1; k++) {
			System.out.println("Etapa " + k);
			System.out.println("Encontrar la columna " + k + " de L y la fila "
					+ k + " de U.");
			System.out.println("Matriz A");
			imprimirMatriz(A, n);
			double suma = 0;
			for (int p = 0; p < k - 1; p++) {
				suma += L[k - 1][p] * U[p][k - 1];
			}
			U[k - 1][k - 1] = Math.sqrt(A[k - 1][k - 1] - suma);
			L[k - 1][k - 1] = U[k - 1][k - 1];

			for (int j = k + 1; j < n + 1; j++) {
				suma = 0;
				for (int p = 0; p < k - 1; p++) {

					suma += L[j - 1][p] * U[p][k - 1];
				}
				L[j - 1][k - 1] = (A[j - 1][k - 1] - suma) / L[k - 1][k - 1];
			}
			System.out.println("Matriz L");
			imprimirMatriz(L, n);
			for (int i = k + 1; i < n + 1; i++) {
				suma = 0;
				for (int p = 0; p < k - 1; p++) {
					suma += L[k - 1][p] * U[p][i - 1];
				}
				U[k - 1][i - 1] = (A[k - 1][i - 1] - suma) / L[k - 1][k - 1];
			}
			System.out.println("Matriz U");
			imprimirMatriz(U, n);
		}

		System.out.println("\nSustitución Progresiva Lz=b");
		double[] z = sustitucionProgresiva(L, b);
		System.out.println("Z:" + Arrays.toString(z));
		Resultado.setText("Z:" + Arrays.toString(z));
		System.out.println("\nSustitución Regresiva Ux=z");
		double[] x = sustitucionRegresiva(U, z);
		for (int i = 0; i < x.length; i++) {
			System.out.println("X" + (i + 1) + " = " + x[i]);
			Resultado.setText("X" + (i + 1) + " = " + x[i]);
		}
	}

}