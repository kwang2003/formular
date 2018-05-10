package com.pachiraframework.formular;

/**
 * 解析器计算返回结果封装
 * @author KevinWang
 *
 */
public class ValueWrapper {
	private Object value;
	public ValueWrapper(Object value) {
		this.value = value;
	}
	
	/**
	 * 静态工厂方法
	 * @param text
	 * @return
	 */
	public static ValueWrapper of(Object value) {
		ValueWrapper result = new ValueWrapper(value);
		return result;
	}
	
	/**
	 * 转换成Boolean类型
	 * @return
	 */
	public Boolean booleanValue() {
		if(this.value == null) {
			return Boolean.FALSE;
		}
		if(this.value instanceof Boolean) {
			return (Boolean)this.value;
		}else if(this.value instanceof String) {
			return Boolean.valueOf((String)this.value);
		}
		throw new IllegalArgumentException(this.value+"不能识别为Boolean类型");
	}
	
	/**
	 * 将结果转化成Double类型
	 * @return
	 */
	public Double doubleValue() {
		if(this.value == null) {
			return null;
		}
		if(this.value instanceof Number) {
			return ((Number)this.value).doubleValue();
		}else if(this.value instanceof String) {
			return Double.valueOf((String)this.value);
		}
		throw new IllegalArgumentException(this.value+"不能识别为Double类型");
	}
	
	public Object getValue() {
		return this.value;
	}
	
	/**
	 * 返回String类型的值
	 * @return
	 */
	public String stringValue() {
		return String.valueOf(this.value);
	}
}
