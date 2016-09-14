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
	
	public void childrenSolve() {
		leftExpression = leftExpression.solve();
		rightExpression = rightExpression.solve();
	}

	public void setNewChild(LogicalExpression nodeFather, LogicalExpression child, LogicalExpression newChild) {
		if (nodeFather instanceof BiImplication) {
			BiImplication father = (BiImplication) nodeFather;

			if (father.getLeftExpression().equals(child)) {
				father.setLeftExpression(newChild);
			} else {
				father.setRightExpression(newChild);
			}
		} else if (nodeFather instanceof Implication) {
			Implication father = (Implication) nodeFather;

			if (father.getLeftExpression().equals(child)) {
				father.setLeftExpression(newChild);
			} else {
				father.setRightExpression(newChild);
			}
		} else if (nodeFather instanceof Disjunction) {
			Disjunction father = (Disjunction) nodeFather;

			if (father.getLeftExpression().equals(this)) {
				father.setLeftExpression(newChild);
			} else {
				father.setRightExpression(newChild);
			}
		} else if (nodeFather instanceof Conjunction) {
			Conjunction father = (Conjunction) nodeFather;

			if (father.getLeftExpression().equals(child)) {
				father.setLeftExpression(newChild);
			} else {
				father.setRightExpression(newChild);
			}
		} else if (nodeFather instanceof Negation) {
			Negation father = (Negation) nodeFather;

			father.setChild(newChild);
		}
	}
}
