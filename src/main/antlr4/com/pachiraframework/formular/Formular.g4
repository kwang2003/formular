grammar Formular;
expr
	: expr '^' expr																# Power
	| expr '/' expr																# Divide
	| expr '*' expr																# Multiply
    | expr '-' expr																# Subtract
    | expr '+' expr																# Add
    | FLOAT																		# Float
    | INTEGER				 													# Integer
    | '(-'expr')' 			 													# Negative
    | '('expr')'             													# Parens
    | IF'(' booleanValue ',' expr ',' expr ')'									# If
    | POWER'('expr ',' expr')'													# Power
    | ABS'('expr')'                     										# Abs
    | MAX'('expr (','expr)*')'													# Max
    | MIN'('expr (','expr)*')'													# Min
    | ROUND'('expr ',' INTEGER')'												# Round
    | AVERAGE'('expr (','expr)*')'												# Average
    | ASIN'('expr')'															# Asin
    | SIN'('expr')'																# Sin
    | ATAN'('expr')'															# Atan
    | TAN'('expr')'															    # Tan
    | ACOS'('expr')'															# Acos
    | COS'('expr')'																# Cos
    | MOD'('expr','INTEGER')'													# Mod
    | INT'('expr')'																# Int
    | PI'()'																	# Pi
;											

booleanValue
	: expr op=COMPARATOR expr													# Comparator
	| BOOLEAN																	# Boolean
	| OR'(' booleanValue (',' booleanValue)* ')'								# Or
	| AND'(' booleanValue (',' booleanValue)* ')'								# And
	| NOT'(' booleanValue ')'													# Not
;

//excel函数名
ABS									:[aA][bB][sS];								//绝对值
MAX									:[mM][aA][xX];								//返回一组值中的最大值。MAX(number1, [number2], ...)
MIN									:[mM][iI][nN];								//返回一组值中的最小值。MIN(number1, [number2], ...)
ROUND								:[rR][oO][uU][nN][dD];						//函数将数字四舍五入到指定的位数=ROUND(A1, 2),A1 包含 23.7825,此函数的结果为 23.78。
IF									:[iI][fF];									//IF判断函数
OR									:[oO][rR];									//OR函数
AND									:[aA][nN][dD];								//AND函数
NOT									:[nN][oO][tT];								//NOT函数
AVERAGE								:[aA][vV][eE][rR][aA][gG][eE];				//average平均数函数
POWER								:[pP][oO][wW][eE][rR];						//power函数
ASIN								:[aA][sS][iI][nN];							//ASIN正玄函数
SIN									:[sS][iI][nN];								//SIN正玄函数
ATAN								:[aA][tT][aA][nN];							//ATAN函数
TAN								    :[tT][aA][nN];							    //TAN函数
ACOS								:[aA][cC][oO][sS];							//ACOS函数
COS									:[cC][oO][sS];								//COS函数
MOD									:[mM][oO][dD];								//MOD函数
PI									:[pP][iI];									//PI圆周率
INT									:[iI][nN][tT];								//INT函数

//数字相关匹配
INTEGER								:[0-9]+;									//整数，包含正整数、负整数、零
FLOAT								:[0-9]* '.' INTEGER+;						//浮点数，包含正浮点数，负浮点数、零(0.0)
BOOLEAN								:([tT][rR][uU][eE])|([fF][aA][lL][sS][eE]);		//Boolean类型，TRUE或者FALSE

//操作符
COMPARATOR							:'<>'|'>='|'<='|'>'|'<'|'=';				//数字/文本比较符号返回 Boolean结果



WHITE_SPACE 						: [ \t\r]+ -> skip ;						//空白定义，可以是空格、制表符，换行符
EULERS_NUMBER: 'e'|'E';
