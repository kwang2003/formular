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
        IF(OR(A2>1,A2<100),100,50)
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
        IF(AND(A2>1,A2<100),100,50)
    - 示例
    ```java
    	private static void testAnd() {
    		assertEquals("if(And(3>4,1>2),1,2)", 2D);
    		assertEquals("if(And(FALSE,TRUE),10,2)", 2D);
    		assertEquals("if(And(1>2,TRue),1,2)", 2D);
    		assertEquals("IF(And(FALSE,OR(3>2)),3,2)", 2D);
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