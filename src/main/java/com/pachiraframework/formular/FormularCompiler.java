package com.pachiraframework.formular;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class FormularCompiler {
	public static ValueWrapper compile(String expression) throws Exception {
		ANTLRInputStream stream = new ANTLRInputStream(expression);
		FormularLexer lexer = new FormularLexer(stream);
		CommonTokenStream tkn = new CommonTokenStream(lexer);
		FormularParser parser = new FormularParser(tkn);
//		parser.setErrorHandler(new BailErrorStrategy());
		ParseTree tree = parser.expr();
		FormularVisitorImpl visitor = new FormularVisitorImpl();
		ValueWrapper ans = visitor.visit(tree);
		return ans;
	}
}
