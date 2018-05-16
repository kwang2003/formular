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
		runAllTest();
	}	
	
	/**
	 * 运行所有测试用例
	 */
	public static void runAllTest() {
		//正数常量
		testPositiveNumber();
		
		//(-A1)负数，因为-可以代表操作符减号，也可以表示负数标识位，为了区分操作符减号与负数标识号，将负数表示为(-A1),即负数必须用括号包含起来
		testNegativeNumber();
		
		//加减混合运算
		testAddSubstract();
		
		//abs函数
		testAbs();
		
		//min函数
		testMin();
		
		//max函数
		testMax();
		
		//()函数
		testParens();
		
		//+加法函数
		testAdd();
		
		//*乘法
		testMultiply();
		
		//-除法
		testDivide();
		
		//round函数
		testRound();
		
		//if函数
		testIf();
		
		//or函数
		testOr();
		
		//and 函数
		testAnd();
		
		//average函数
		testAverage();
		
		//power 幂函数
		testPower();
		
		//pi 函数
		testPi();
		
		//sin 正弦函数
		testSin();
		
		//cos 余弦函数
		testCos();
		
		//not 函数
		testNot();
		
		//int函数
		testInt();
		
		//acos函數
		testAcos();
		
		//asin函数
		testAsin();
		
		//atan函数
		testAtan();
		
		//tan函数
		testTan();
		
		//mod函数
		testMod();
	}
	
	private static void testMod() {
		assertEquals("MOD(3, 2)", 1D);
		assertEquals("MOD((-3), 2)", 1D);
	}
	
	private static void testTan() {
		assertEquals("TAN(0.785)", 0.9992039901050427);
		assertEquals("TAN(45*PI()/180)", 0.9999999999999999D);
	}
	
	private static void testAtan() {
		assertEquals("ATAN(1)", 0.7853981633974483);
		assertEquals("ATAN(1)*180/PI()", 45D);
	}
	
	private static void testAsin() {
		assertEquals("ASIN((-0.5))*180/PI()", -30.000000000000004D);
		assertEquals("ASIN((-0.5))", -0.5235987755982989D);
	}
	
	private static void testAcos() {
		assertEquals("ACOS((-0.5))*180/PI()", 120.00000000000001D);
	}
	
	private static void testInt() {
		assertEquals("int(1.12)", 1D);
		assertEquals("INT((-8.9))", -9D);
		assertEquals("int(1.12+2)", 3D);
		assertEquals("int(cos(min(3,0,5)))", 1D);
	}
	
	private static void testNot() {
		assertEquals("if(not(true),3,1)", 1D);
		assertEquals("if(not(and(3>1,4>2)),3,1)", 1D);
		assertEquals("if(not(1>2),1,2)", 1D);
	}
	
	private static void testPi() {
		assertEquals("pi()", Math.PI);
		assertEquals("PI()*(3^2)", 28.274333882308138);//圆的面积
	}
	
	private static void testSin() {
		assertEquals("sin(0)", 0D);
		assertEquals("sin(min(3,0,5))", 0D);
	}
	
	private static void testCos() {
		assertEquals("cos(0)", 1D);
		assertEquals("cos(min(3,0,5))", 1D);
	}
	
	public static void testPower() {
		assertEquals("5^2", 25D);
		assertEquals("2^10", 1024D);
		assertEquals("6^2/4", 9D);
		assertEquals("2+5^2", 27D);
		assertEquals("POWER(5,2)", 25D);
		assertEquals("POWER(1,2)", 1D);
		assertEquals("POWER(3,3)", 27D);
		assertEquals("POWER(max(4,5,3),2)", 25D);
		assertEquals("POWER(5+2,2)", 49D);
		assertEquals("POWER(5-2*2+1,2)", 4D);
	}
	
	public static void testAverage() {
		assertEquals("Average(3)", 3D);
		assertEquals("Average(3,3)", 3D);
		assertEquals("Average(2,4)", 3D);
		assertEquals("Average(3.4,2.6)", 3D);
		assertEquals("Average(3,average(3))", 3D);
		assertEquals("Average(3+3,2)", 4D);
		assertEquals("Average(max(3,4,5),7)", 6D);
	}
	
	private static void testAnd() {
		assertEquals("if(And(3>4,1>2),1,2)", 2D);
		assertEquals("if(And(FALSE,TRUE),10,2)", 2D);
		assertEquals("if(And(1>2,TRue),1,2)", 2D);
		assertEquals("IF(And(FALSE,OR(3>2)),3,2)", 2D);
	}
	
	private static void testOr() {
		assertEquals("if(OR(3>4,1>2),1,2)", 2D);
		assertEquals("if(OR(FALSE,TRUE),10,2)", 10D);
		assertEquals("if(OR(1>2,TRue),1,2)", 1D);
		assertEquals("IF(OR(FALSE,OR(3>2)),3,2)", 3D);
	}
	
	/**
	 * 测试if函数
	 */
	private static void testIf() {
		assertEquals("IF(1>2,3,4)", 4D);
		assertEquals("IF(1<2,3,4)", 3D);
		assertEquals("IF(1=1,1,4)", 1D);
		assertEquals("IF(1>=1,1,4)", 1D);
		assertEquals("IF(1>=1.1,1,8)", 8D);
		assertEquals("IF(1+2>=1,1,4)", 1D);
		assertEquals("IF(1-2>=1,1,4)", 4D);
		assertEquals("IF(3/(-1.5)>0,abs(1.5),min(5,6))", 5D);
		assertEquals("IF(1+3*8/(2+2)-18/3*4>2,3,4)", 4D);
		assertEquals("IF(1<>2,3,4)", 3D);
		assertEquals("IF(1<>1,3,4)", 4D);
		assertEquals("IF(1>2,3,IF(2>4,6,7))", 7D);
	}
	
	private static void testRound() {
		assertEquals("round(23.7825,2)", 23.78D);
		assertEquals("round(2,2)", 2.00D);
		assertEquals("round(2.123,2)", 2.12D);
		assertEquals("round(2.126,2)", 2.13D);
		assertEquals("round((-2.126),2)", -2.13D);
		assertEquals("round(abs(2.126),2)", 2.13D);
	}
	/**
	 * 测试除法
	 */
	private static void testDivide() {
		assertEquals("3/2", 1.5D);
		assertEquals("(-3)/(-2)", 1.5D);
		assertEquals("3/1.5", 2D);
		assertEquals("(-3)/1.5", -2D);
		assertEquals("3/(-1.5)", -2D);
		assertEquals("3/2+3", 4.5D);
		assertEquals("3/2/1.5", 1D);
		assertEquals("abs(3/2)", 1.5D);
		assertEquals("1-3/2+2", 1.5D);
		assertEquals("1+8/(2+2)-5", -2D);
		assertEquals("18/3*4", 24D);
		assertEquals("5*8/2", 20D);
		assertEquals("5*8/2-18", 2D);
		assertEquals("1+3*8/(2+2)-18/3*4", -17D);
	}
	
	/**
	 * 测试乘法
	 */
	private static void testMultiply() {
		assertEquals("3*2", 6D);
		assertEquals("3.2*2", 6.4D);
		assertEquals("3.2*0", 0D);
		assertEquals("3*2*5", 30D);
		assertEquals("3*abs(2)*5", 30D);
		assertEquals("3*2*max(5,3,2)", 30D);
		assertEquals("1+2*3", 7D);
		assertEquals("1+(2*3)", 7D);
		assertEquals("(1+2)*3", 9D);
		assertEquals("1+2*(4+6)", 21D);
		assertEquals("1+2*(4+6)-1+10", 30D);
		assertEquals("1+2*(4-6)-1+10", 6D);
	}
	
	/**
	 * 测试加减混合运算
	 */
	public static void testAddSubstract() {
		assertEquals("(-1)+3", 2D);
		assertEquals("(-1)-(-2)", 1D);
		assertEquals("3+2+1", 6D);
		assertEquals("3+(-8)+2+1", -2D);
		assertEquals("3-2+1", 2D);
		assertEquals("3.5-2.2+1", 2.3D);
		assertEquals("1-6+abs(3)+max(3,abs((-4)),abs(6))", 4D);		
		assertEquals("3-2+10", 11D);
	}
	/**
	 * 测试()运算
	 */
	private static void testParens() {
		assertEquals("(1)", 1D);
		assertEquals("((1))", 1D);
	}
	
	/**
	 * 测试加法运算
	 */
	private static void testAdd() {
		assertEquals("(1+1)", 2D);
		assertEquals("1+2", 3D);
		assertEquals("1+ABS(2)", 3D);
		assertEquals("(1)+(2)", 3D);
		assertEquals("(1+4.2)", 5.2D);
		assertEquals("1+max(1,2,3)", 4D);
		assertEquals("1+max(1,2,ABS(3))", 4D);
	}
	
	private static void testAbs() {
		assertEquals("abs(1)", 1D);
		assertEquals("abs((-1))", 1D);
		assertEquals("abs(10)", 10D);
		assertEquals("abs(10.1)", 10.1D);
		assertEquals("ABS((-11.0))", 11D);
	}
	/**
	 * min函数测试
	 */
	private static void testMin() {
		assertEquals("min(1)", 1D);
		assertEquals("MIN(1)", 1D);
		assertEquals("min(1,2)", 1D);
		assertEquals("min(1,2,3)", 1D);
		assertEquals("min(1,1.1,3)", 1D);
		assertEquals("min(1,(-1.1),3)", -1.1D);
		assertEquals("min(abs((-1)),1.1,3)", 1D);
		assertEquals("min(abs((-1)),aBs(1.1),min(3,4,6))", 1D);
	}
	
	/**
	 * max函数测试
	 */
	private static void testMax() {
		assertEquals("max(1)", 1D);
		assertEquals("MAX(1)", 1D);
		assertEquals("max(1,2)", 2D);
		assertEquals("max(1,2,3)", 3D);
		assertEquals("max(1,1.1,3)", 3D);
		assertEquals("max(1,(-1.1),3)", 3D);
		assertEquals("max(abs((-1)),1.1,3)", 3D);
		assertEquals("max(abs((-1)),aBs(1.1),max(3,4,6))", 6D);
	}
	
	/**
	 * 正数测试
	 */
	private static void testPositiveNumber() {
		assertEquals("343", 343D);
		assertEquals("3.43", 3.43D);
		assertEquals(".343", .343D);
		assertEquals("0343", 343D);
		assertEquals("0", 0D);
		assertEquals("0.0", 0D);
		assertEquals("1", 1D);
		assertEquals("24.13", 24.13D);
	}
	
	private static void testNegativeNumber() {
		assertEquals("(-1)", -1D);
		assertEquals("(-1.2)", -1.2D);
		assertEquals("(-3456)", -3456D);
		assertEquals("(-343)", -343D);
		assertEquals("(-3.43)", -3.43D);
	}
	
	
	private static ValueWrapper beforeAssert(String formular,Object result) throws Exception{
		System.out.println("##################################");
		System.out.print("输入表达式：");
		System.out.println(formular);
		System.out.println("期望结果："+result);
		return FormularCompiler.compile(formular);
	}
	
	private static void afterAssert(Object expected,Object actual) {
		System.out.println("实际结果："+actual);
		System.out.println("是否匹配："+actual.equals(expected));
		if(!actual.equals(expected)) {
			throw new IllegalArgumentException("不匹配，期望值："+expected+",实际值:"+actual);
		}
		System.out.println();
	}
	
	/**
	 * 测试用例，对输入的表达式做断言判断
	 * @param formular 表达式
	 * @param result	期望结果
	 */
	private static void assertEquals(String formular,Double result) {
		try {
			ValueWrapper value = beforeAssert(formular, result);
			Double actual = value.doubleValue();
			afterAssert(result, actual);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	private static void assertEquals(String formular,Boolean result) {
//		try {
//			ValueWrapper value = beforeAssert(formular, result);
//			Boolean actual = value.booleanValue();
//			afterAssert(result, actual);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
