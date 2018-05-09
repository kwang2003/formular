package com.pachiraframework.formular;

/**
 * Unit test for simple App.
 */
public class AppTest{
	public static void main(String[] args) {
		System.out.println(Double.valueOf("-0").equals(0D));
		String s="9.86";
	    Float f =  Float.valueOf(s);  
	    System.out.println(f);
	    f =f*100;  
	    Long result = f.longValue();  
	    System.out.println(result);
	    
	    Double d =  Double.valueOf(s);  
	    d = d*100;  
	    System.out.println(d.longValue());
	    System.out.println(3.0F/1000F);
	}
}

