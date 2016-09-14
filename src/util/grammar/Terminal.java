package util.grammar;

public class Terminal extends LogicalExpression {
	protected Boolean booleanValue;
	protected String value;
	
	public Terminal(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	
	public Terminal(String value) {
		this.value = value;
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
