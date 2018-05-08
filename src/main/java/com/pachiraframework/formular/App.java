package com.pachiraframework.formular;

/**
 * @author KevinWang
 *
 */
public class App {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		assertEquals("343", 343D);
		assertEquals("-343", -343D);
		assertEquals("3.43", 3.43D);
		assertEquals(".343", .343D);
		assertEquals("0343", 343D);
		assertEquals("-3.43", -3.43D);
		assertEquals("0", 0D);
		assertEquals("0.0", 0D);
		assertEquals("1", 1D);
		assertEquals("24.13", 24.13D);
		
		//abs函数
		assertEquals("abs(1)", 1D);
		assertEquals("abs(-1)", 1D);
		assertEquals("abs(10)", 10D);
		assertEquals("abs(10.1)", 10.1D);
		assertEquals("ABS(-11.0)", 11D);
		
		//min函数
		assertEquals("min(1)", 1D);
		assertEquals("MIN(1)", 1D);
		assertEquals("min(1,2)", 1D);
		assertEquals("min(1,2,3)", 1D);
		assertEquals("min(1,1.1,3)", 1D);
		assertEquals("min(1,-1.1,3)", -1.1D);
		assertEquals("min(abs(-1),1.1,3)", 1D);
		assertEquals("min(abs(-1),aBs(1.1),min(3,4,6))", 1D);
		
		//max函数
		assertEquals("max(1)", 1D);
		assertEquals("MAX(1)", 1D);
		assertEquals("max(1,2)", 2D);
		assertEquals("max(1,2,3)", 3D);
		assertEquals("max(1,1.1,3)", 3D);
		assertEquals("max(1,-1.1,3)", 3D);
		assertEquals("max(abs(-1),1.1,3)", 3D);
		assertEquals("max(abs(-1),aBs(1.1),max(3,4,6))", 6D);
		
		//()函数
		assertEquals("(1)", 1D);
		assertEquals("((1))", 1D);
		assertEquals("(-1)", -1D);
		
		//+加法函数
		assertEquals("(1+1)", 2D);
		assertEquals("1+2", 3D);
		assertEquals("1+ABS(2)", 3D);
		assertEquals("(1)+(2)", 3D);
		assertEquals("(1+4.2)", 5.2D);
		assertEquals("1+max(1,2,3)", 4D);
		assertEquals("1+max(1,2,ABS(3))", 4D);
		
		//-减法
		assertEquals("-1", -1D);
		assertEquals("-1.2", -1.2D);
		assertEquals("-0", -0D);
		assertEquals("-0.0", -0D);
	}
	
	/**
	 * 测试用例，对输入的表达式做断言判断
	 * @param formular 表达式
	 * @param result	期望结果
	 */
	private static void assertEquals(String formular,Double result) {
		System.out.println("##################################");
		System.out.println("输入表达式：");
		System.out.println(formular);
		System.out.println("期望结果："+result);
		try {
			Double actual = FormularCompiler.compile(formular);
			System.out.println("实际结果："+actual);
			System.out.println("是否匹配："+actual.equals(result));
			if(!actual.equals(result)) {
				throw new IllegalArgumentException("不匹配，期望值："+result+",实际值:"+actual);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
	}

}
