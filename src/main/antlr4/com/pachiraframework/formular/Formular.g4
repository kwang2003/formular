grammar Formular;
expr: expr '*' expr						# Multiply
	| expr '/' expr						# Divide
    | expr '+' expr						# Add
    | expr '-' expr						# Subtract
    | FLOAT								# Float
    | INTEGER				 			# Integer
    | '('expr')'             			# Parens
    | ABS'('expr')'                     # abs
    | MAX'('expr (','expr)*')'			# max
    | MIN'('expr (','expr)*')'			# min
    ;


//excel函数名
ABS									:[aA][bB][sS];										//绝对值
MAX									:[mM][aA][xX];										//返回一组值中的最大值。MAX(number1, [number2], ...)
MIN									:[mM][iI][nN];										//返回一组值中的最小值。MIN(number1, [number2], ...)

//数字相关匹配
INTEGER								:[-]?[0-9]+;										//整数，包含正整数、负整数、零
FLOAT								:[-]?[0-9]* '.' INTEGER+;							//浮点数，包含正浮点数，负浮点数、零(0.0)


WHITE_SPACE 						: [ \t\r]+ -> skip ;								//空白定义，可以是空格、制表符，换行符
EULERS_NUMBER: 'e'|'E';
