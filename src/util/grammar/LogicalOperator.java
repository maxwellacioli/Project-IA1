package util.grammar;

public enum LogicalOperator {
	
	CONJUNCTION ("^"),
	DISJUNCTION ("v"),
	IMPLICATION (">"),
	BIIMPLICATION ("<>"),
	NEGATION ("~");
	
	private String opValue;

	private LogicalOperator(String opValue) {
		this.opValue = opValue;
	}
	
	public String getLogicalOperatorValue() {
		return opValue;
	}
}
