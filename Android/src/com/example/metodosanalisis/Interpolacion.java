package com.example.metodosanalisis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Interpolacion extends Activity {
	TableLayout xPoint;
	TableLayout yPoint;
	EditText[] pointX;
	EditText[] pointY;
	CheckBox newton;
	CheckBox lagrange;
	CheckBox neville;
	int points;
	EditText value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interpolacion);
		points = this.getIntent().getExtras().getInt("numberOfPoints");
		xPoint = (TableLayout) findViewById(R.id.xPoint);
		yPoint = (TableLayout) findViewById(R.id.yPoint);
		pointX = new EditText[points];
		pointY = new EditText[points];
		value  = (EditText)findViewById(R.id.value);
		newton = (CheckBox) findViewById(R.id.Newton);
		lagrange = (CheckBox) findViewById(R.id.Lagrange);
		neville = (CheckBox) findViewById(R.id.Neville);

		this.fillPointX(points, xPoint);
		this.fillPointY(points, yPoint);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interpolacion, menu);
		return true;
	}

	private void fillPointX(final int n, TableLayout table) {
		table.removeAllViews();
		TableRow row = new TableRow(this);
		row.setLayoutParams(new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT));
		for (int i = 0; i < n; i++) {
			pointX[i] = new EditText(this);

			pointX[i].setInputType(InputType.TYPE_CLASS_NUMBER
					| InputType.TYPE_NUMBER_FLAG_DECIMAL
					| InputType.TYPE_NUMBER_FLAG_SIGNED);
			pointX[i].setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			// edit.setText(Double.toString(editBArray[i]));
			pointX[i].setHint("X" + i);

			row.addView(pointX[i]);
		}
		table.addView(row);

	}

	private void fillPointY(final int n, TableLayout table) {
		table.removeAllViews();
		TableRow row = new TableRow(this);
		row.setLayoutParams(new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT));
		for (int i = 0; i < n; i++) {
			pointY[i] = new EditText(this);

			pointY[i].setInputType(InputType.TYPE_CLASS_NUMBER
					| InputType.TYPE_NUMBER_FLAG_DECIMAL
					| InputType.TYPE_NUMBER_FLAG_SIGNED);
			pointY[i].setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			// edit.setText(Double.toString(editBArray[i]));
			pointY[i].setHint("Y" + i);

			row.addView(pointY[i]);
		}
		table.addView(row);

	}

	public double[] getValuesOfPointX() {

		double[] vectorX = new double[points];
		double[] vectorY = new double[points];
		for (int i = 0; i < points; i++) {

			vectorX[i] = Double.parseDouble(pointX[i].getText().toString());
			vectorY[i] = Double.parseDouble(pointY[i].getText().toString());

		}

		return vectorX;

	}

	public double[] getValuesOfPointY() {

		double[] vectorY = new double[points];
		for (int i = 0; i < points; i++) {

			vectorY[i] = Double.parseDouble(pointY[i].getText().toString());

		}

		return vectorY;

	}

	public void calcular(View v) {

		Intent next = new Intent(this, SolucionInterpolacion.class);
		SolucionInterpolacion.vectorX = getValuesOfPointX();
		SolucionInterpolacion.vectorY = getValuesOfPointY();
		double doubleValue = Double.parseDouble(value.getText().toString());
		
		if (newton.isChecked() && !lagrange.isChecked() && !neville.isChecked()) {
			next.putExtra("numberOfPoints", points);
			next.putExtra("valueToEvaluate", doubleValue);
			next.putExtra("method", 1);
			startActivity(next);

		} else if (!newton.isChecked() && lagrange.isChecked()
				&& !neville.isChecked()) {
			next.putExtra("numberOfPoints", points);
			next.putExtra("valueToEvaluate", doubleValue);
			next.putExtra("method", 2);
			startActivity(next);
		} else if (!newton.isChecked() && !lagrange.isChecked()
				&& neville.isChecked()) {
			next.putExtra("numberOfPoints", points);
			next.putExtra("valueToEvaluate", doubleValue);
			next.putExtra("method", 3);
			startActivity(next);
		}else{
			
		}
		
	}

}
