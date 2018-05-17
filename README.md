完整代码地址:[https://github.com/kwang2003/formular](https://github.com/kwang2003/formular)
### 概述
基于antlr4实现Excel公式计算功能,目前已经实现的函数操作：
- 负数(-)
    - 说明
    
        负数要带括号因为负号可能被解析成操作符减号
    - 示例
    ```java
    	private static void testNegativeNumber() {
    		assertEquals("(-1)", -1D);
    		assertEquals("(-1.2)", -1.2D);
    		assertEquals("(-3456)", -3456D);
    		assertEquals("(-343)", -343D);
    		assertEquals("(-3.43)", -3.43D);
    	}
    ```
- 加法运算+
    - 说明
        
        数据之间做加法运算
    - 示例
    ```java
    	private static void testAdd() {
    		assertEquals("(1+1)", 2D);
    		assertEquals("1+2", 3D);
    		assertEquals("1+ABS(2)", 3D);
    		assertEquals("(1)+(2)", 3D);
    		assertEquals("(1+4.2)", 5.2D);
    		assertEquals("1+max(1,2,3)", 4D);
    		assertEquals("1+max(1,2,ABS(3))", 4D);
    	}
    ```
- 减法运算-
    - 说明
        
        数字之间进行减法运算
    - 示例
    ```java
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
    	private static void testAdd() {
    		assertEquals("(1+1)", 2D);
    		assertEquals("1+2", 3D);
    		assertEquals("1+ABS(2)", 3D);
    		assertEquals("(1)+(2)", 3D);
    		assertEquals("(1+4.2)", 5.2D);
    		assertEquals("1+max(1,2,3)", 4D);
    		assertEquals("1+max(1,2,ABS(3))", 4D);
    	}
    ```
- 括弧运算符()
    - 说明
        
        括弧运算符包含的表达式，其结果还是表达式本身的值
    - 示例

    ```java
    	private static void testParens() {
    		assertEquals("(1)", 1D);
    		assertEquals("((1))", 1D);
    	}
    ```
- 乘法运算*
    - 说明
        
        数值之间相乘
    - 示例
    ```java
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
    ```
- 除法运算
    - 说明
    
        数值之间除法运算
    - 示例
    ```java
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
    ```

- [ABS 函数](https://support.office.com/zh-cn/article/abs-%E5%87%BD%E6%95%B0-3420200f-5628-4e8c-99da-c99d7c87713c)
	- 说明
		
		取绝对值
	- 语法

		ABS(number)

		ABS 函数语法具有下列参数

		- **number**    必需。 需要计算其绝对值的实数。

	- 示例

    ```java
    	private static void testAbs() {
    		assertEquals("abs(1)", 1D);
    		assertEquals("abs((-1))", 1D);
    		assertEquals("abs(10)", 10D);
    		assertEquals("abs(10.1)", 10.1D);
    		assertEquals("ABS((-11.0))", 11D);
    	}
    ```

- [MAX 函数](https://support.office.com/zh-cn/article/max-%E5%87%BD%E6%95%B0-e0012414-9ac8-4b34-9a47-73e662c08098)
	- 说明
		
		返回一组值中的最大值。
	- 语法

		MAX(number1, [number2], ...)

		MAX 函数语法具有下列参数

		- **number1, number2, ...**    Number1 是必需的，后续数字是可选的。 要从中查找最大值的 1 到 255 个数字。

	- 示例
    ```java
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
    ```

- [MIN 函数](https://support.office.com/zh-cn/article/min-%E5%87%BD%E6%95%B0-61635d12-920f-4ce2-a70f-96f202dcc152)
	- 说明
		
		返回一组值中的最小值。
	- 语法

		MIN(number1, [number2], ...)

		MIN 函数语法具有下列参数

		- **number1, number2, ...**    Number1 是必需的，后续数字是可选的。 要从中查找最小值的 1 到 255 个数字。

	- 示例

    ```java
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
    ```

- [ROUND 函数](https://support.office.com/zh-cn/article/round-%E5%87%BD%E6%95%B0-c018c5d8-40fb-4053-90b1-b3e7f61a213c)
    - 说明
         
        ROUND 函数将数字四舍五入到指定的位数。 例如，如果单元格 A1 包含 23.7825，而且您想要将此数值舍入到两个小数位数，可以使用以下公式：
    
        =ROUND(A1, 2)

        此函数的结果为 23.78。
    - 语法
        
        ROUND(number, num_digits)
        
        ROUND 函数语法具有下列参数：
        
        - **number**    必需。 要四舍五入的数字。
        - **num_digits**    必需。 要进行四舍五入运算的位数。
    - 备注
        - 如果 num_digits 大于 0（零），则将数字四舍五入到指定的小数位数。
        - 如果 num_digits 等于 0，则将数字四舍五入到最接近的整数。
        - ~~如果 num_digits 小于 0，则将数字四舍五入到小数点左边的相应位数。（暂不支持）~~
        - 若要始终进行向上舍入（远离 0），请使用 ROUNDUP 函数。
        - 若要始终进行向下舍入（朝向 0），请使用 ROUNDDOWN 函数。
        - 若要将某个数字四舍五入为指定的倍数（例如，四舍五入为最接近的 0.5 倍），请使用 MROUND 函数。
    - 示例
        
    ```java
    	private static void testRound() {
    		assertEquals("round(23.7825)", 23.78D);
    		assertEquals("round(2,2)", 2.00D);
    		assertEquals("round(2.123,2)", 2.12D);
    		assertEquals("round(2.126,2)", 2.13D);
    		assertEquals("round((-2.126),2)", -2.13D);
    		assertEquals("round(abs(2.126),2)", 2.13D);
    	}
    ```
- [INT 函数](https://support.office.com/zh-cn/article/int-%E5%87%BD%E6%95%B0-a6c4af9e-356d-4369-ab6a-cb1fd9d343ef)
    - 说明
    
        将数字向下舍入到最接近的整数
    - 语法
    
        Int( number )

        INT 函数语法具有下列参数：
        - **Number**    必需。 需要进行向下舍入取整的实数
    - 示例
    ```java
	private static void testInt() {
		assertEquals("int(1.12)", 1D);
		assertEquals("INT((-8.9))", -9D);
		assertEquals("int(1.12+2)", 3D);
		assertEquals("int(cos(min(3,0,5)))", 1D);
	}    
    ```
- [IF 函数](https://support.office.com/zh-cn/article/if-%E5%87%BD%E6%95%B0-69aed7c9-4e8a-4755-a9bc-aa8bbff73be2)
    - 说明
        对值和期待值进行逻辑比较。IF 函数最简单的形式表示：
        - 如果（内容为 True，则执行某些操作，否则就执行其他操作）
    - 语法
        
        IF(logical_test, value_if_true, value_if_false) 
    - 示例
    ```java
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
    ```
- [OR 函数](https://support.office.com/zh-cn/article/or-%E5%87%BD%E6%95%B0-7d17ad14-8700-4281-b308-00b131e22af0)
    - 说明

        使用 OR 函数，它是一个逻辑函数，用于确定测试中的所有条件是否均为 TRUE。
    - 语法
        OR(logical_test1,[logical_test2],...)
    - 示例
    ```java
    	private static void testOr() {
    		assertEquals("if(OR(3>4,1>2),1,2)", 2D);
    		assertEquals("if(OR(FALSE,TRUE),10,2)", 10D);
    		assertEquals("if(OR(1>2,TRue),1,2)", 1D);
    		assertEquals("IF(OR(FALSE,OR(3>2)),3,2)", 3D);
    	}
    ```
- [AND 函数](https://support.office.com/zh-cn/article/and-%E5%87%BD%E6%95%B0-5f19b2e8-e1df-4408-897a-ce285a19e9d9)
    - 说明

        使用 AND 函数，它是一个逻辑函数，用于确定测试中的所有条件是否均为 TRUE。
    - 语法
        AND(logical_test1,[logical_test2],...)
    - 示例
    ```java
    	private static void testAnd() {
    		assertEquals("if(And(3>4,1>2),1,2)", 2D);
    		assertEquals("if(And(FALSE,TRUE),10,2)", 2D);
    		assertEquals("if(And(1>2,TRue),1,2)", 2D);
    		assertEquals("IF(And(FALSE,OR(3>2)),3,2)", 2D);
    	}
    ```
- [NOT 函数](https://support.office.com/zh-cn/article/not-%E5%87%BD%E6%95%B0-9cfc6011-a054-40c7-a140-cd4ba2d87d77)
    - 说明
        
         **NOT** 函数会对其参数的值进行求反。

         **NOT** 函数的一个常见用途是扩展执行逻辑测试的其它函数的有效性。例如，IF 函数将执行逻辑测试，并在计算结果为 TRUE 时返回一个值，在计算结果为 FALSE 时返回另一个值。通过将 NOT 函数作为 IF 函数的 logical_test 参数，你可以测试众多而不仅是单个条件。
    - 语法
        
         **NOT**（逻辑函数）
    - 示例
    ```java
	private static void testNot() {
		assertEquals("if(not(true),3,1)", 1D);
		assertEquals("if(not(and(3>1,4>2)),3,1)", 1D);
		assertEquals("if(not(1>2),1,2)", 1D);
	}    
    ```
- [AVERAGE 函数](https://support.office.com/zh-cn/article/average-%E5%87%BD%E6%95%B0-047bac88-d466-426c-a32b-8f33eb960cf6) 
    - 说明
        
        返回参数的平均值（算术平均值）。 例如，如果范围A1,A20 包含数字，则公式 =AVERAGE(A1,A20) 将返回这些数字的平均值。
    - 语法
    
        AVERAGE(number1, [number2], ...)
        
        AVERAGE 函数语法具有下列参数：
        - **Number1**    必需。 要计算平均值的第一个数字、单元格引用或单元格区域
        - **Number2, ...**    可选。 要计算平均值的其他数字、单元格引用或单元格区域，最多可包含 255 个
    - 示例
    ```java
    	public static void testAverage() {
    		assertEquals("Average(3)", 3D);
    		assertEquals("Average(3,3)", 3D);
    		assertEquals("Average(2,4)", 3D);
    		assertEquals("Average(3.4,2.6)", 3D);
    		assertEquals("Average(3,average(3))", 3D);
    		assertEquals("Average(3+3,2)", 4D);
    		assertEquals("Average(max(3,4,5),7)", 6D);
    	}    
    ```
- [POWER 函数](https://support.office.com/zh-cn/article/power-%E5%87%BD%E6%95%B0-d3f2908b-56f4-4c3f-895a-07fb519c362a)
    - 说明
        
        返回数字乘幂的结果。
    - 语法
        
        POWER(number, power)
        POWER 函数语法具有下列参数：
        - **Number**    必需。 基数。 可为任意实数
        - **power**    必需。 基数乘幂运算的指数
    - 备注
        
        可以使用“^”代替 POWER，以表示基数乘幂运算的幂，例如 5^2。
    - 示例
    ```java
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
    ```
- [PI 函数](https://support.office.com/zh-cn/article/pi-%E5%87%BD%E6%95%B0-264199d0-a3ba-46b8-975a-c4a04608989b)
    - 说明
        
        返回数字 3.14159265358979（数学常量 pi），精确到 15 个数字。
    - 语法
    
        标准偏差

        PI 函数语法没有参数
    - 示例
    ```java
	private static void testPi() {
		assertEquals("pi()", Math.PI);
		assertEquals("PI()*(3^2)", 28.274333882308138);//圆的面积
	}
    ```

- [SIN 函数](https://support.office.com/zh-cn/article/sin-%E5%87%BD%E6%95%B0-cf0e3432-8b9e-483c-bc55-a76651c95602)
    - 说明
        
        返回已知角度的正弦
    - 语法
        
        Sin( number )

        SIN 函数语法具有下列参数：
        
        - **Number**    必需。 需要求正弦的角度，以弧度表示。
    - 备注
        
        如果参数是以度数表示的，请将它乘以 PI()/180 或使用 RADIANS 函数将它转换为弧度。
    - 示例
    ```java
	private static void testSin() {
		assertEquals("sin(0)", 0D);
		assertEquals("sin(min(3,0,5))", 0D);
	}
    ```
- [COS 函数](https://support.office.com/zh-cn/article/cos-%E5%87%BD%E6%95%B0-0fb808a5-95d6-4553-8148-22aebdce5f05)
    - 说明
        
        返回已知角度的余弦值
    - 语法
    
        COS(number)

        COS 函数语法具有下列参数：
        - **“数字”**    必需。 想要求余弦的角度，以弧度表示。
    - 备注
    
        如果角度是以度表示的，则可将其乘以 PI()/180 或使用 RADIANS 函数将其转换成弧度。
    - 示例
    ```java
	private static void testCos() {
		assertEquals("cos(0)", 1D);
		assertEquals("cos(min(3,0,5))", 1D);
	}    
    ```
- [ACOS 函数](https://support.office.com/zh-cn/article/acos-%E5%87%BD%E6%95%B0-cb73173f-d089-4582-afa1-76e5524b5d5b)
    - 说明
        返回数字的反余弦值。 反余弦值是指余弦值为 number 的角度。 返回的角度以弧度表示，弧度值在 0（零）到 pi 之间。
    - 语法
    
        ACOS(number)

        ACOS 函数语法具有以下参数：
        - **Number**    必需。 所求角度的余弦值，必须介于 -1 到 1 之间
    - 示例
    ```java
	private static void testAcos() {
		assertEquals("ACOS((-0.5))*180/PI()", 120.00000000000001D);
	}    
    ```
- [ASIN 函數](https://support.office.com/zh-cn/article/asin-%E5%87%BD%E6%95%B0-81fb95e5-6d6f-48c4-bc45-58f955c6d347)
    - 说明
        
        返回数字的反正弦值。 反正弦值是指正弦值为 number 的角度。 返回的角度以弧度表示，弧度值在 -pi/2 到 pi/2 之间。
    - 语法
        
        ASIN(number)

        ASIN 函数语法具有以下参数：
        - **Number**    必需。 所求角度的正弦值，必须介于 -1 到 1 之间。
    - 示例
    ```java
	private static void testAsin() {
		assertEquals("ASIN((-0.5))*180/PI()", -30.000000000000004D);
		assertEquals("ASIN((-0.5))", -0.5235987755982989D);
	}    
    ```
- [TAN 函数](https://support.office.com/zh-cn/article/tan-%E5%87%BD%E6%95%B0-08851a40-179f-4052-b789-d7f699447401)
    - 说明
    
        返回已知角度的正切
    - 语法
        
        TAN(number)

        TAN 函数语法具有下列参数：
        - **Number**    必需。 要求正切的角度，以弧度表示。
    - 示例
    ```java
	private static void testTan() {
		assertEquals("TAN(0.785)", 0.9992039901050427);
		assertEquals("TAN(45*PI()/180)", 0.9999999999999999D);
	}    
    ```
- [ATAN 函数](https://support.office.com/zh-cn/article/atan-%E5%87%BD%E6%95%B0-50746fa8-630a-406b-81d0-4a2aed395543)
    - 说明
    
        返回数字的反正切值。 反正切值是指正切值为 number 的角度。 返回的角度以弧度表示，弧度值在 -pi/2 到 pi/2 之间。
    - 语法
    
        ATAN(number)

        ATAN 函数语法具有以下参数：
        - **Number**    必需。 所求角度的正切值
    - 示例
    ```java
 	private static void testAtan() {
		assertEquals("ATAN(1)", 0.7853981633974483);
		assertEquals("ATAN(1)*180/PI()", 45D);
	}   
    ```
- [RADIANS 函数](https://support.office.com/zh-cn/article/radians-%E5%87%BD%E6%95%B0-ac409508-3d48-45f5-ac02-1497c92de5bf)    
    - 说明
    
        将度数转换为弧度
    - 语法
    
        RADIANS(angle)

        RADIANS 函数语法具有下列参数：
        - **Angle**    必需。 要转换的以度数表示的角度
    - 示例
    ```java
	private static void testRadians() {
		assertEquals("radians(270)", 4.71238898038469);
	}    
    ```
- [RAND 函数](https://support.office.com/zh-cn/article/rand-%E5%87%BD%E6%95%B0-4cbfa695-8869-4788-8d90-021ea9f5be73)
    - 说明
    
         RAND 返回了一个大于等于 0 且小于 1 的平均分布的随机实数。每次计算工作表时都会返回一个新的随机实数。
    - 语法
        
        RAND

        RAND 函数语法没有参数。
    - 示例
    ```java
	private static void testRand() throws Exception{
		System.out.println(FormularCompiler.compile("rand()").doubleValue());
		System.out.println(FormularCompiler.compile("rand()*(100+10)").doubleValue());
	}    
    ```
- [SQRT 函数](https://support.office.com/zh-cn/article/sqrt-%E5%87%BD%E6%95%B0-654975c2-05c4-4831-9a24-2c65e4040fdf)
    - 说明
    
        返回正的平方根
    - 语法
    
        SQRT(number)

        SQRT 函数语法具有下列参数：
        - **Number**    必需。 要计算其平方根的数字。
    - 示例
    ```java
	private static void testSqrt() {
		assertEquals("sqrt(9)", 3d);
		assertEquals("sqrt(2*5-6)", 2D);
		assertEquals("1*sqrt(9)+5*2", 13d);
		assertEquals("sqrt(max(9,5,min(1,2,7)))", 3d);
	}    
    ```
- [SUM 函数](https://support.office.com/zh-cn/article/sum-%E5%87%BD%E6%95%B0-043e1c7d-7726-4e80-8f32-07b23e057f89)
    - 说明
    
         SUM 函数是一个数学和三角函数，可将值相加。你可以将单个值、单元格引用或是区域相加，或者将三者的组合相加。
    - 语法
    
        SUM(number1, [number2], ...)
    - 示例
    ```java
	private static void testSum() {
		assertEquals("sum(1)", 1D);
		assertEquals("sum(1,2)", 3D);
		assertEquals("2+sum(1,2)", 5D);
		assertEquals("2*3+sum(1,2)*5", 21D);
		assertEquals("2*3+sum(1,min(2,3,4))*5", 21D);
	}    
    ```
- [SUMSQ 函数](https://support.office.com/zh-cn/article/sumsq-%E5%87%BD%E6%95%B0-e3313c02-51cc-4963-aae6-31442d9ec307)
    - 说明
    
        返回参数的平方和
    - 语法
        
        SUMSQ(number1, [number2], ...)

        SUMSQ 函数语法具有下列参数：
        - **number1, number2, ...**    Number1 是必需的，后续数字是可选的。 要对其求平方和的 1 到 255 个参数。 也可以用单一数组或对某个数组的引用来代替用逗号分隔的参数
    - 示例
    ```java
	private static void testSumSq() {
		assertEquals("sumsq(1)", 1D);
		assertEquals("sumsq(1,2)", 5D);
		assertEquals("sumsq(3,4)", 25D);
		assertEquals("1+sumsq(1,2)*2", 11D);
	}    
    ```
- [MOD 函数](https://support.office.com/zh-cn/article/mod-%E5%87%BD%E6%95%B0-9b6cd169-b6ee-406a-a97b-edf2a9dc24f3)
    - 说明
        
        返回两数相除的余数。 结果的符号与除数相同。
    - 语法
        
        MOD(number, divisor)

        MOD 函数语法具有下列参数：
        - **Number**    必需。 要计算余数的被除数。
        - **Divisor**    必需。 除数。

    - 示例
    ```java
	private static void testMod() {
		assertEquals("MOD(3, 2)", 1D);
		assertEquals("MOD((-3), 2)", 1D);
	}    
    ```


### 运行环境
- jdk1.8
- antlr4 4.5,antlr4-maven-plugin 4.5
	> **注意**:把项目依赖升级到最新版本的antlr 4.7和antlr4-maven-plugin 4.7后不能正常运行，原因暂未定位

#### 添加依赖

antlr4

```
<dependency>
	<groupId>org.antlr</groupId>
	<artifactId>antlr4-runtime</artifactId>
	<version>4.5</version>
</dependency>
```

添加maven插件antlr4-maven-plugin

```
<build>
	<plugins>
		<plugin>
			<groupId>org.antlr</groupId>
			<artifactId>antlr4-maven-plugin</artifactId>
			<version>4.5</version>
			<executions>
				<execution>
					<id>antlr</id>
					<goals>
						<goal>antlr4</goal>
					</goals>
				</execution>
			</executions>
			<configuration>
				<arguments>
					<argument>-no-listener</argument>
					<argument>-visitor</argument>
				</arguments>
				<treatWarningsAsErrors>true</treatWarningsAsErrors>
			</configuration>
		</plugin>
	</plugins>
</build>
```

#### 测试结果
测试用例位于 **com.pachiraframework.formular.App.main()** 方法中 

参考资料
- [Excel 函数（按字母顺序）](https://support.office.com/zh-cn/article/excel-%e5%87%bd%e6%95%b0%ef%bc%88%e6%8c%89%e5%ad%97%e6%af%8d%e9%a1%ba%e5%ba%8f%ef%bc%89-b3944572-255d-4efb-bb96-c6d90033e188?ui=zh-CN&rs=zh-CN&ad=CN#bm18)