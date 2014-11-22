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
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Eliminacion_Gauss extends Activity {

	CheckBox parcial;
	CheckBox total;
	CheckBox sinPivoteo;
	CheckBox escalonado;
	int sizeOfMatrix;
	TableLayout table;
	EditText[][] editMatrix;
	TextView Resultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_eliminacion__gauss);
		parcial = (CheckBox) findViewById(R.id.PivoteoParcial);
		total = (CheckBox) findViewById(R.id.PivoteoTotal);
		sinPivoteo = (CheckBox) findViewById(R.id.SinPivoteo);
		escalonado = (CheckBox) findViewById(R.id.PivoteoEscalonado);
		Resultado = (TextView) findViewById(R.id.resultado);

		sizeOfMatrix = getIntent().getExtras().getInt("size");
		editMatrix = new EditText[sizeOfMatrix][sizeOfMatrix + 1];

		table = (TableLayout) findViewById(R.id.tableLayout);
		this.fillTable(sizeOfMatrix, table);

	}

	private void fillTable(final int n, TableLayout table) {
		table.removeAllViews();
		for (int i = 0; i < n; i++) {
			TableRow row = new TableRow(this);
			row.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			for (int j = 0; j < n + 1; j++) {
				editMatrix[i][j] = new EditText(this);
				editMatrix[i][j].setInputType(InputType.TYPE_CLASS_NUMBER
						| InputType.TYPE_NUMBER_FLAG_DECIMAL
						| InputType.TYPE_NUMBER_FLAG_SIGNED);
				editMatrix[i][j].setLayoutParams(new TableRow.LayoutParams(
						TableRow.LayoutParams.WRAP_CONTENT,
						TableRow.LayoutParams.WRAP_CONTENT));

				// edit.setText(Double.toString(matrix[i][j]));
				if (sizeOfMatrix == j) {
					editMatrix[i][j].setHint("B" + i);
				} else {
					editMatrix[i][j].setHint("M" + i + j);
				}
				editMatrix[i][j].setEnabled(true);
				row.addView(editMatrix[i][j]);
			}
			table.addView(row);
		}
	}

	public double[][] getValuesOfMatrix() {
		double[][] matrix = new double[sizeOfMatrix][sizeOfMatrix + 1];

		for (int i = 0; i < sizeOfMatrix; i++) {

			for (int j = 0; j < sizeOfMatrix + 1; j++) {

				matrix[i][j] = Double.parseDouble(editMatrix[i][j].getText()
						.toString());
			}
		}

		return matrix;

	}

	public void calcular(View v) {

		Log.i("mierda", "mierda");
		double[][] matrix = this.getValuesOfMatrix();

		// this.eliminacionGaussPivoteoParcial(4, matrix);

		if (parcial.isChecked() && !total.isChecked()
				&& !sinPivoteo.isChecked() && !escalonado.isChecked()) {
			this.eliminacionGaussPivoteoParcial(sizeOfMatrix, matrix);
		} else if (sinPivoteo.isChecked() && !parcial.isChecked()
				&& !total.isChecked() && !escalonado.isChecked()) {
			this.eliminacionGaussianaSimple(sizeOfMatrix, matrix);

		} else if (total.isChecked() && !parcial.isChecked()
				&& !sinPivoteo.isChecked() && !escalonado.isChecked()) {
			this.eliminacionGaussPivoteoTotal(sizeOfMatrix, matrix);
		} else if (escalonado.isChecked() && !parcial.isChecked()
				&& !total.isChecked() && !sinPivoteo.isChecked()) {
			this.eliminacionGaussPivoteoEscalonado(sizeOfMatrix, matrix);
		} else {

			Log.i("perra", "perra");
		}
	}

	// Se implementa pivoteoParcial

	public void eliminacionGaussPivoteoParcial(int n, double[][] matrix) {
		System.out.println("Matriz Original");
		imprimirMatriz(matrix, n);

		for (int k = 1; k < n; k++) {
			System.out.println("Etapa " + k);
			pivoteoParcial(matrix, k, n);
			System.out.println("Objetivo: Poner ceros debajo del elemento A"
					+ k + "," + k + "= " + matrix[k - 1][k - 1]);
			System.out.println("\nMultiplicadores:");
			for (int i = k + 1; i < n + 1; i++) {
				double multiplicador = matrix[i - 1][k - 1]
						/ matrix[k - 1][k - 1];
				for (int j = k; j < n + 2; j++) {
					matrix[i - 1][j - 1] = matrix[i - 1][j - 1] - multiplicador
							* matrix[k - 1][j - 1];
				}
				System.out.println("Multiplicador" + i + "," + k + " : "
						+ multiplicador);
			}
			System.out.println(" ");
			imprimirMatriz(matrix, n);
		}
		double x[] = new double[n];
		System.out.println("Sustitución Regresiva");
		for (int i = n; i > 0; i--) {
			double sumatoria = 0;
			for (int p = i + 1; p <= n; p++) {
				sumatoria = sumatoria + matrix[i - 1][p - 1] * x[p - 1];
			}
			x[i - 1] = (matrix[i - 1][n] - sumatoria) / matrix[i - 1][i - 1];
			System.out.println("X" + i + " = " + x[i - 1]);
			Resultado.setText("X" + i + " = " + x[i - 1]);
		}
	}

	public double[][] pivoteoParcial(double[][] A, int k, int n) {
		double elementoMayor = Math.abs(A[k - 1][k - 1]);
		int filaMayor = k - 1;
		for (int s = k - 1; s < n; s++) {
			double nuevoElemento = Math.abs(A[s][k - 1]);
			if (nuevoElemento > elementoMayor) {
				elementoMayor = Math.abs(A[s][k - 1]);
				filaMayor = s;
			}
		}
		System.out.println("Elemento Mayor: " + elementoMayor + " en la fila "
				+ (filaMayor + 1));
		if (elementoMayor == 0) {
			System.out.println("El sistema no tiene solución única.");
			System.exit(0);
		} else {
			if (filaMayor != k - 1) {
				System.out.println("Cambio de fila " + k + " con fila "
						+ (filaMayor + 1));
				for (int i = 0; i < A[0].length; i++) {
					double aux = A[k - 1][i];
					A[k - 1][i] = A[filaMayor][i];
					A[filaMayor][i] = aux;
				}
				imprimirMatriz(A, n);
			}
		}
		return A;
	}

	public void imprimirMatriz(double[][] matrix, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n + 1; j++) {
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

	// Se implementa pivoteoTotal
	public void eliminacionGaussPivoteoTotal(int n, double[][] matrix) {
		System.out.println("Matriz Original");
		int[] marcas = crearMarcas(n);
		imprimirMatriz(matrix, n, marcas);
		for (int k = 1; k < n; k++) {
			System.out.println("Etapa " + k);
			pivoteoTotal(matrix, marcas, k, n);
			System.out.println("Objetivo: Poner ceros debajo del elemento A"
					+ k + "," + k + "= " + matrix[k - 1][k - 1]);
			System.out.println("\nMultiplicadores:");
			for (int i = k + 1; i < n + 1; i++) {
				double multiplicador = matrix[i - 1][k - 1]
						/ matrix[k - 1][k - 1];
				for (int j = k; j < n + 2; j++) {
					matrix[i - 1][j - 1] = matrix[i - 1][j - 1] - multiplicador
							* matrix[k - 1][j - 1];
				}
				System.out.println("Multiplicador" + i + "," + k + " : "
						+ multiplicador);
			}
			System.out.println(" ");
			imprimirMatriz(matrix, n, marcas);
		}
		double x[] = new double[n];
		System.out.println("Sustitución Regresiva");
		for (int i = n; i > 0; i--) {
			double sumatoria = 0;
			for (int p = i + 1; p <= n; p++) {
				sumatoria = sumatoria + matrix[i - 1][p - 1] * x[p - 1];
			}
			x[i - 1] = (matrix[i - 1][n] - sumatoria) / matrix[i - 1][i - 1];
			System.out.println("X" + marcas[i - 1] + " = " + x[i - 1]);
			Resultado.setText("X" + i + " = " + x[i - 1]);
		}
	}

	public int[] crearMarcas(int n) {
		int[] marcas = new int[n];
		for (int i = 0; i < n; i++) {
			marcas[i] = i + 1;
		}
		return marcas;
	}

	public double[][] pivoteoTotal(double[][] matrix, int[] marcas, int k, int n) {
		double mayor = 0;
		int filaMayor = k - 1;
		int columnaMayor = k - 1;
		for (int r = k - 1; r < n; r++) {
			for (int s = k - 1; s < n; s++) {
				if (Math.abs(matrix[r][s]) > mayor) {
					mayor = Math.abs(matrix[r][s]);
					filaMayor = r;
					columnaMayor = s;
				}
			}
		}
		System.out.println("Elemento Mayor: " + mayor + " en la fila "
				+ (filaMayor + 1) + " y la columna " + (columnaMayor + 1));
		if (mayor == 0) {
			System.out.println("El sistema no tiene solución única.");
			System.exit(0);
		} else {
			if (filaMayor != k - 1) {
				System.out.println("Cambio de fila " + k + " con fila "
						+ (filaMayor + 1));
				for (int i = 0; i < matrix[0].length; i++) {
					double aux = matrix[k - 1][i];
					matrix[k - 1][i] = matrix[filaMayor][i];
					matrix[filaMayor][i] = aux;
				}
				imprimirMatriz(matrix, n, marcas);
			}
			if (columnaMayor != k - 1) {
				System.out.println("Cambio de columna " + k
						+ " con la columna " + (columnaMayor + 1));
				for (int i = 0; i < n; i++) {
					double aux = matrix[i][k - 1];
					matrix[i][k - 1] = matrix[i][columnaMayor];
					matrix[i][columnaMayor] = aux;
				}
				int aux2 = marcas[columnaMayor];
				marcas[columnaMayor] = marcas[k - 1];
				marcas[k - 1] = aux2;
				imprimirMatriz(matrix, n, marcas);
			}
		}
		return matrix;
	}

	public void imprimirMatriz(double[][] matrix, int n, int[] marcas) {
		for (int i = 0; i < n; i++) {
			System.out.print("X" + marcas[i]);
			printSpaces(String.valueOf(marcas[i]).length(), 30);
		}
		System.out.print("\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n + 1; j++) {
				System.out.print(matrix[i][j]);
				printSpaces(String.valueOf(matrix[i][j]).length(), 30);
			}
			System.out.print("\n");
		}
		System.out.println("");
	}

	// Se implementa sinPivoteo

	public void eliminacionGaussianaSimple(int n, double[][] matrix) {
		System.out.println("Matriz Original");
		imprimirMatriz(matrix, n);

		for (int k = 1; k < n; k++) {
			System.out.println("Etapa " + k);
			System.out.println("Objetivo: Poner ceros debajo del elemento A"
					+ k + "," + k + "= " + matrix[k - 1][k - 1]);
			System.out.println("\nMultiplicadores:");
			for (int i = k + 1; i < n + 1; i++) {
				double multiplicador = matrix[i - 1][k - 1]
						/ matrix[k - 1][k - 1];
				for (int j = k; j < n + 2; j++) {
					matrix[i - 1][j - 1] = matrix[i - 1][j - 1] - multiplicador
							* matrix[k - 1][j - 1];
				}

				System.out.println("Multiplicador" + i + "," + k + " : "
						+ multiplicador);
			}
			System.out.println(" ");
			imprimirMatriz(matrix, n);
		}
		double x[] = new double[n];
		System.out.println("Sustitución Regresiva");
		for (int i = n; i > 0; i--) {
			double sumatoria = 0;
			for (int p = i + 1; p <= n; p++) {
				sumatoria = sumatoria + matrix[i - 1][p - 1] * x[p - 1];
			}
			x[i - 1] = (matrix[i - 1][n] - sumatoria) / matrix[i - 1][i - 1];
			System.out.println("X" + i + " = " + x[i - 1]);
			Resultado.setText("X" + i + " = " + x[i - 1]);
		}
	}

	// Se implementa pivoteoEscalonado
	public double[] busquedaDelMayorDeCadaFila(int n, double[][] matrix) {
		double[] s = new double[n];
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (Math.abs(matrix[i - 1][j - 1]) > s[i - 1]) {
					s[i - 1] = Math.abs(matrix[i - 1][j - 1]);
				}
			}
		}
		return s;
	}

	public void eliminacionGaussPivoteoEscalonado(int n, double[][] matrix) {

		System.out.println("Matriz Original");
		imprimirMatriz(matrix, n);
		double[] s = busquedaDelMayorDeCadaFila(n, matrix);
		System.out.println("Vector de mayores de cada fila");
		System.out.println(Arrays.toString(s));
		System.out.println("");
		for (int k = 1; k < n; k++) {
			System.out.println("Etapa " + k);

			pivoteoEscalonado(matrix, k, n, s);

			System.out.println("");

			System.out.println("Objetivo: Poner ceros debajo del elemento A"
					+ k + "," + k + "= " + matrix[k - 1][k - 1]);

			System.out.println("\nMultiplicadores:");
			for (int i = k + 1; i < n + 1; i++) {
				double multiplicador = matrix[i - 1][k - 1]
						/ matrix[k - 1][k - 1];
				for (int j = k; j < n + 2; j++) {
					matrix[i - 1][j - 1] = matrix[i - 1][j - 1] - multiplicador
							* matrix[k - 1][j - 1];
				}
				System.out.println("Multiplicador" + i + "," + k + " : "
						+ multiplicador);
			}
			System.out.println(" ");
			imprimirMatriz(matrix, n);
		}
		double x[] = new double[n];
		System.out.println("Sustitución Regresiva");
		for (int i = n; i > 0; i--) {
			double sumatoria = 0;
			for (int p = i + 1; p <= n; p++) {
				sumatoria = sumatoria + matrix[i - 1][p - 1] * x[p - 1];
			}
			x[i - 1] = (matrix[i - 1][n] - sumatoria) / matrix[i - 1][i - 1];
			System.out.println("X" + i + " = " + x[i - 1]);
			Resultado.setText("X" + i + " = " + x[i - 1]);
		}
	}

	public void pivoteoEscalonado(double[][] matrix, int k, int n, double[] s) {

		double mayor = 0;
		int filamayor = k - 1;

		double[] cocientes = new double[n];

		for (int i = k; i < n + 1; i++) {
			cocientes[i - 1] = Math.abs(matrix[i - 1][k - 1]) / s[i - 1];
		}
		System.out.println("");
		System.out.println("Vector de cocientes");
		System.out.println(Arrays.toString(cocientes));

		for (int i = k - 1; i < n; i++) {
			if (cocientes[i] > mayor) {
				mayor = cocientes[i];
				filamayor = i;
			}
		}
		System.out.println("El cociente mayor es: " + mayor + " de la fila "
				+ (filamayor + 1));
		if (mayor == 0) {
			System.out.println("El sistema no tiene solución única.");
			System.exit(0);
		} else {
			if (filamayor != k - 1) {
				System.out.println("");
				System.out.println("Cambio de fila " + k + " con fila "
						+ (filamayor + 1));
				for (int i = 0; i < matrix[0].length; i++) {
					double aux = matrix[k - 1][i];
					matrix[k - 1][i] = matrix[filamayor][i];
					matrix[filamayor][i] = aux;
				}
				double aux2 = s[k - 1];
				s[k - 1] = s[filamayor];
				s[filamayor] = aux2;

				System.out.println("Matriz A");
				imprimirMatriz(matrix, n);
				System.out.println("Vector de mayores de cada fila");
				System.out.println(Arrays.toString(s));
			}
		}
	}

}
