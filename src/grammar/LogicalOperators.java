package grammar;

public enum LogicalOperators {
	
	CONJUNCTION ("^"),
	DISJUNCTION ("v"),
	IMPLICATION (">"),
	BIIMPLICATION ("<>"),
	NEGATION ("~");
	
	private String opValue;

	private LogicalOperators(String opValue) {
		this.opValue = opValue;
	}
	
	public String getEnumValue() {
		return opValue;
	}
}
