### 概述
基于antlr4实现Excel公式计算功能,目前已经实现的函数操作：
- [ABS 函数](https://support.office.com/zh-cn/article/abs-%E5%87%BD%E6%95%B0-3420200f-5628-4e8c-99da-c99d7c87713c)
	- 说明
		
		取绝对值
	- 语法

		ABS(number)

		ABS 函数语法具有下列参数

		- **number**    必需。 需要计算其绝对值的实数。

	- 示例

		ABS(2) 2 的绝对值。

		ABS(-2) -2 的绝对值

- [MAX 函数](https://support.office.com/zh-cn/article/max-%E5%87%BD%E6%95%B0-e0012414-9ac8-4b34-9a47-73e662c08098)
	- 说明
		
		返回一组值中的最大值。
	- 语法

		MAX(number1, [number2], ...)

		MAX 函数语法具有下列参数

		- **number1, number2, ...**    Number1 是必需的，后续数字是可选的。 要从中查找最大值的 1 到 255 个数字。

	- 示例


		MAX(A2,A6) A2,A6 中的最大值。

		MAX(123,2345,1235,199)
- [MIN 函数](https://support.office.com/zh-cn/article/min-%E5%87%BD%E6%95%B0-61635d12-920f-4ce2-a70f-96f202dcc152)
	- 说明
		
		返回一组值中的最小值。
	- 语法

		MIN(number1, [number2], ...)

		MIN 函数语法具有下列参数

		- **number1, number2, ...**    Number1 是必需的，后续数字是可选的。 要从中查找最小值的 1 到 255 个数字。

	- 示例

		MIN(A2,A6) A2,A6 中的最小值。

		MIN(123,2345,1235,199)

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