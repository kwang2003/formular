package com.pachiraframework.formular;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.pachiraframework.formular.FormularParser.AbsContext;
import com.pachiraframework.formular.FormularParser.AcosContext;
import com.pachiraframework.formular.FormularParser.AddContext;
import com.pachiraframework.formular.FormularParser.AndContext;
import com.pachiraframework.formular.FormularParser.AsinContext;
import com.pachiraframework.formular.FormularParser.AtanContext;
import com.pachiraframework.formular.FormularParser.AverageContext;
import com.pachiraframework.formular.FormularParser.BooleanContext;
import com.pachiraframework.formular.FormularParser.ComparatorContext;
import com.pachiraframework.formular.FormularParser.CosContext;
import com.pachiraframework.formular.FormularParser.DivideContext;
import com.pachiraframework.formular.FormularParser.ExprContext;
import com.pachiraframework.formular.FormularParser.FloatContext;
import com.pachiraframework.formular.FormularParser.IfContext;
import com.pachiraframework.formular.FormularParser.IntContext;
import com.pachiraframework.formular.FormularParser.IntegerContext;
import com.pachiraframework.formular.FormularParser.MaxContext;
import com.pachiraframework.formular.FormularParser.MinContext;
import com.pachiraframework.formular.FormularParser.ModContext;
import com.pachiraframework.formular.FormularParser.MultiplyContext;
import com.pachiraframework.formular.FormularParser.NegativeContext;
import com.pachiraframework.formular.FormularParser.NotContext;
import com.pachiraframework.formular.FormularParser.OrContext;
import com.pachiraframework.formular.FormularParser.PiContext;
import com.pachiraframework.formular.FormularParser.PowerContext;
import com.pachiraframework.formular.FormularParser.RoundContext;
import com.pachiraframework.formular.FormularParser.SinContext;
import com.pachiraframework.formular.FormularParser.SubtractContext;
import com.pachiraframework.formular.FormularParser.TanContext;

public class FormularVisitorImpl extends FormularBaseVisitor<ValueWrapper> {
	@Override
	public ValueWrapper visitParens(FormularParser.ParensContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public ValueWrapper visitDivide(DivideContext ctx) {
		ValueWrapper left = visit(ctx.expr(0));
		ValueWrapper right = visit(ctx.expr(1));
		return ValueWrapper.of(String.valueOf((left.doubleValue() / right.doubleValue())));
	}

	@Override
	public ValueWrapper visitMultiply(MultiplyContext ctx) {
		ValueWrapper left = visit(ctx.expr(0));
		ValueWrapper right = visit(ctx.expr(1));
		return ValueWrapper.of(left.doubleValue() * right.doubleValue());
	}

	@Override
	public ValueWrapper visitAdd(AddContext ctx) {
		ValueWrapper left = visit(ctx.expr(0));
		ValueWrapper right = visit(ctx.expr(1));
		return ValueWrapper.of(left.doubleValue() + right.doubleValue());
	}

	@Override
	public ValueWrapper visitSubtract(SubtractContext ctx) {
		ValueWrapper left = visit(ctx.expr(0));
		ValueWrapper right = visit(ctx.expr(1));
		return ValueWrapper.of(left.doubleValue() - right.doubleValue());
	}

	@Override
	public ValueWrapper visitAbs(AbsContext ctx) {
		ValueWrapper number = visit(ctx.expr());
		return ValueWrapper.of(Math.abs(number.doubleValue()));
	}

	@Override
	public ValueWrapper visitMax(MaxContext ctx) {
		ValueWrapper max =visit(ctx.expr(0));
		int size = ctx.expr().size();
		for(int i =1;i < size; i++) {
			ValueWrapper value = visit(ctx.expr(i));
			if(value.doubleValue() > max.doubleValue()) {
				max = value;
			}
		}
		return max;
	}

	@Override
	public ValueWrapper visitMin(MinContext ctx) {
		ValueWrapper min =visit(ctx.expr(0));
		int size = ctx.expr().size();
		for(int i =1;i < size; i++) {
			ValueWrapper value = visit(ctx.expr(i));
			if(value.doubleValue() < min.doubleValue()) {
				min = value;
			}
		}
		return min;
	}

	@Override
	public ValueWrapper visitInteger(IntegerContext ctx) {
		String text = ctx.getText();
		return ValueWrapper.of(text);
	}

	@Override
	public ValueWrapper visitFloat(FloatContext ctx) {
		String text = ctx.getText();
		return ValueWrapper.of(text);
	}

	/**
	 * 负数(-A1)  (-1)
	 */
	@Override
	public ValueWrapper visitNegative(NegativeContext ctx) {
		ValueWrapper value =visit(ctx.expr());
		return ValueWrapper.of((-1D*value.doubleValue()));
	}

	@Override
	public ValueWrapper visitRound(RoundContext ctx) {
		ValueWrapper value =visit(ctx.expr());
		String digits = ctx.INTEGER().getText();
		BigDecimal b = new BigDecimal(value.doubleValue());
		double rs = b.setScale(Integer.valueOf(digits), BigDecimal.ROUND_HALF_UP).doubleValue();
		return ValueWrapper.of(rs);
	}

	@Override
	public ValueWrapper visitIf(IfContext ctx) {
		ValueWrapper booleanResult = visit(ctx.booleanValue());
		return booleanResult.booleanValue() ? visit(ctx.expr(0)):visit(ctx.expr(1));
	}


	@Override
	public ValueWrapper visitBoolean(BooleanContext ctx) {
		String text = ctx.getText();
		return ValueWrapper.of(Boolean.valueOf(text));
	}


	@Override
	public ValueWrapper visitAnd(AndContext ctx) {
		int size = ctx.booleanValue().size();
		for(int i =0;i < size; i++) {
			ValueWrapper value = visit(ctx.booleanValue(i));
			if(!value.booleanValue()) {
				return value;
			}
		}
		return ValueWrapper.of(true);
	}

	@Override
	public ValueWrapper visitOr(OrContext ctx) {
		int size = ctx.booleanValue().size();
		for(int i =0;i < size; i++) {
			ValueWrapper value = visit(ctx.booleanValue(i));
			if(value.booleanValue()) {
				return value;
			}
		}
		return ValueWrapper.of(false);
	}

	@Override
	public ValueWrapper visitComparator(ComparatorContext ctx) {
		String op = ctx.op.getText();
		OperatorEnum operator = OperatorEnum.of(op);
		ValueWrapper one = visit(ctx.expr(0));
		ValueWrapper two = visit(ctx.expr(1));
		boolean rs = false;
		switch(operator) {
			case EQUAL : {
				rs = one.getValue().equals(two.getValue());
				break;
			}
			case EQUAL_OR_GREATER_THAN : {
				rs = one.doubleValue() >= two.doubleValue();
				break;
			}
			case EQUAL_OR_LESS_THAN : {
				rs = one.doubleValue() <= two.doubleValue();
				break;
			}
			case GREATER_THAT : {
				rs = one.doubleValue() > two.doubleValue();
				break;
			}
			case LESS_THAN : {
				rs = one.doubleValue() < two.doubleValue();
				break;
			}
			case NOT_EQUAL : {
				rs = !one.getValue().equals(two.getValue());
				break;
			}
		}
		return ValueWrapper.of(rs);
	}

	@Override
	public ValueWrapper visitAverage(AverageContext ctx) {
		Double total = 0D;
		int size = ctx.expr().size();
		for(ExprContext context : ctx.expr()) {
			ValueWrapper value = visit(context);
			total += value.doubleValue();
		}
		return ValueWrapper.of(total/size);
	}

	@Override
	public ValueWrapper visitPower(PowerContext ctx) {
		ValueWrapper one = visit(ctx.expr(0));
		ValueWrapper two = visit(ctx.expr(1));
		return ValueWrapper.of(Math.pow(one.doubleValue(), two.doubleValue()));
	}

	@Override
	public ValueWrapper visitSin(SinContext ctx) {
		ValueWrapper value = visit(ctx.expr());
		return ValueWrapper.of(Math.sin(value.doubleValue()));
	}

	@Override
	public ValueWrapper visitPi(PiContext ctx) {
		return ValueWrapper.of(Math.PI);
	}

	@Override
	public ValueWrapper visitCos(CosContext ctx) {
		ValueWrapper value = visit(ctx.expr());
		return ValueWrapper.of(Math.cos(value.doubleValue()));
	}

	@Override
	public ValueWrapper visitNot(NotContext ctx) {
		ValueWrapper value = visit(ctx.booleanValue());
		return ValueWrapper.of(!value.booleanValue());
	}

	@Override
	public ValueWrapper visitInt(IntContext ctx) {
		ValueWrapper value = visit(ctx.expr());
		return ValueWrapper.of(Math.floor(value.doubleValue()));
	}

	@Override
	public ValueWrapper visitAcos(AcosContext ctx) {
		ValueWrapper value = visit(ctx.expr());
		return ValueWrapper.of(Math.acos(value.doubleValue()));
	}

	@Override
	public ValueWrapper visitAsin(AsinContext ctx) {
		ValueWrapper value = visit(ctx.expr());
		return ValueWrapper.of(Math.asin(value.doubleValue()));
	}

	@Override
	public ValueWrapper visitTan(TanContext ctx) {
		ValueWrapper value = visit(ctx.expr());
		return ValueWrapper.of(Math.tan(value.doubleValue()));
	}

	@Override
	public ValueWrapper visitAtan(AtanContext ctx) {
		ValueWrapper value = visit(ctx.expr());
		return ValueWrapper.of(Math.atan(value.doubleValue()));
	}

	@Override
	public ValueWrapper visitMod(ModContext ctx) {
		ValueWrapper value = visit(ctx.expr());
		BigInteger number = BigInteger.valueOf(value.doubleValue().longValue());
		BigInteger divisor = new BigInteger(ctx.INTEGER().getText());
		return ValueWrapper.of(number.mod(divisor).doubleValue());
	}

	private enum OperatorEnum{
		EQUAL("="),LESS_THAN("<"),GREATER_THAT(">"),EQUAL_OR_LESS_THAN("<="),EQUAL_OR_GREATER_THAN(">="),NOT_EQUAL("<>");
		private String operator;
		private OperatorEnum(String operator) {
			this.operator = operator;
		}
		public String getOperator() {
			return this.operator;
		}
		
		public static OperatorEnum of(String operator) {
			for(OperatorEnum enu : OperatorEnum.values()) {
				if(enu.getOperator().equals(operator)) {
					return enu;
				}
			}
			throw new IllegalArgumentException("非法的请求参数，不能识别"+operator+"为比较符号");
		}
	}
}
