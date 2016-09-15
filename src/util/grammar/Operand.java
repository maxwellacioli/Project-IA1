package util.grammar;

public class Operand extends LogicalExpression {
	protected Boolean booleanValue;
	protected Boolean notFlag = false;
	protected String value;

	public Operand(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public Operand(String value) {
		this.value = value;
	}

	public Boolean getNotFlag() {
		return notFlag;
	}
	
	public void changeNotFlag() {
		notFlag = !notFlag;
	}
	
	public void changeBooleanValue() {
		booleanValue = !booleanValue;
	}
	
	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public String getValue() {
		return value;
	}

	@Override
	public LogicalExpression solve() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public String toString() {
		return value;
	}

}
