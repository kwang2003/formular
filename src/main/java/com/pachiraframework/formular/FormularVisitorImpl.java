package com.pachiraframework.formular;

import java.math.BigDecimal;

import com.pachiraframework.formular.FormularParser.AbsContext;
import com.pachiraframework.formular.FormularParser.AddContext;
import com.pachiraframework.formular.FormularParser.DivideContext;
import com.pachiraframework.formular.FormularParser.FloatContext;
import com.pachiraframework.formular.FormularParser.IntegerContext;
import com.pachiraframework.formular.FormularParser.MaxContext;
import com.pachiraframework.formular.FormularParser.MinContext;
import com.pachiraframework.formular.FormularParser.MultiplyContext;
import com.pachiraframework.formular.FormularParser.NegativeContext;
import com.pachiraframework.formular.FormularParser.RoundContext;
import com.pachiraframework.formular.FormularParser.SubtractContext;

public class FormularVisitorImpl extends FormularBaseVisitor<Double> {
	@Override
	public Double visitParens(FormularParser.ParensContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public Double visitDivide(DivideContext ctx) {
		double left = visit(ctx.expr(0));
		double right = visit(ctx.expr(1));
		return left / right;
	}

	@Override
	public Double visitMultiply(MultiplyContext ctx) {
		double left = visit(ctx.expr(0));
		double right = visit(ctx.expr(1));
		return left * right;
	}

	@Override
	public Double visitAdd(AddContext ctx) {
		double left = visit(ctx.expr(0));
		double right = visit(ctx.expr(1));
		return left + right;
	}

	@Override
	public Double visitSubtract(SubtractContext ctx) {
		double left = visit(ctx.expr(0));
		double right = visit(ctx.expr(1));
		return left - right;
	}

	@Override
	public Double visitAbs(AbsContext ctx) {
		double number = visit(ctx.expr());
		return Math.abs(number);
	}

	@Override
	public Double visitMax(MaxContext ctx) {
		Double max =visit(ctx.expr(0));
		int size = ctx.expr().size();
		for(int i =1;i < size; i++) {
			Double value = visit(ctx.expr(i));
			if(value > max) {
				max = value;
			}
		}
		return max;
	}

	@Override
	public Double visitMin(MinContext ctx) {
		Double min =visit(ctx.expr(0));
		int size = ctx.expr().size();
		for(int i =1;i < size; i++) {
			Double value = visit(ctx.expr(i));
			if(value < min) {
				min = value;
			}
		}
		return min;
	}

	@Override
	public Double visitInteger(IntegerContext ctx) {
		return Double.valueOf(ctx.getText());
	}

	@Override
	public Double visitFloat(FloatContext ctx) {
		String text = ctx.getText();
		return Double.valueOf(text);
	}

	/**
	 * 负数(-A1)  (-1)
	 */
	@Override
	public Double visitNegative(NegativeContext ctx) {
		Double value =visit(ctx.expr());
		return -1D*value;
	}

	@Override
	public Double visitRound(RoundContext ctx) {
		Double value =visit(ctx.expr());
		String digits = ctx.INTEGER().getText();
		BigDecimal b = new BigDecimal(value);
		double rs = b.setScale(Integer.valueOf(digits), BigDecimal.ROUND_HALF_UP).doubleValue();
		return rs;
	}


}
