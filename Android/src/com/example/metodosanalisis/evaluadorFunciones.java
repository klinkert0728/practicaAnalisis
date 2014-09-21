package com.example.metodosanalisis;
import android.content.Context;
import android.util.Log;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
public class evaluadorFunciones {
	private static final String TAG =evaluadorFunciones.class.getName();
	  
	  private static Context c; 
	  private static evaluadorFunciones instance = null;
	  private static Calculable cal;
	  
	  protected evaluadorFunciones () {
	  }
	  
	  public static evaluadorFunciones getInstance(Context c) {
	    if (instance == null) instance = new evaluadorFunciones();
	    instance.setContext(c);
	    return instance;
	  }
	  
	  public void setFunction(String function) throws Exception {
	    try {
	      cal = new ExpressionBuilder(function)
	                  .withVariableNames("x").build();
	    } catch (Exception e) {
	      Log.e(TAG, "Cannot instantiate Calculator with the function: "
	          + function);
	      String message = c.getString(R.string.invalid_function_exception,
	                     function); 
	      throw new Exception(message);
	    }
	  }
	  
	  public double calculate(double xValue) {
	    cal.setVariable("x", xValue);
	    return cal.calculate();
	  }
	  
	  public void setContext(Context c) {
	    this.c = c;
	  }
}
