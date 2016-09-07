package util.grammar;

import util.parser.nodetree.LogicalExpression;

public class Terminal implements LogicalExpression {
	
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
	public LogicalExpression interpret() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
