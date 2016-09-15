package util.grammar;

public class Operand extends LogicalExpression {
	protected Boolean booleanValue;
	protected Boolean notTerminal = false;
	protected String value;

	public Operand(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public Operand(String value) {
		this.value = value;
	}

	public Boolean getNotTerminal() {
		return notTerminal;
	}

	public void setNotTerminal(Boolean notTerminal) {
		this.notTerminal = notTerminal;
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
