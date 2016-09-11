package util.grammar;

public abstract class NonTerminal extends LogicalExpression {
	protected LogicalExpression leftExpression;
	protected LogicalExpression rightExpression;

	public void setLeftExpression(LogicalExpression leftExpression) {
		this.leftExpression = leftExpression;
	}

	public void setRightExpression(LogicalExpression rightExpression) {
		this.rightExpression = rightExpression;
	}

	public LogicalExpression getLeftExpression() {
		return leftExpression;
	}

	public LogicalExpression getRightExpression() {
		return rightExpression;
	}
}
