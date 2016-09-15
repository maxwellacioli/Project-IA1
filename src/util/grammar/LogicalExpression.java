package util.grammar;

public abstract class LogicalExpression {

	public abstract LogicalExpression solve();

	public LogicalExpression walkAST(LogicalExpression node) {
		if (node instanceof Operand) {
			return node;
		}

		if (node instanceof Negation) {
			Negation negation = (Negation) node;
			negation.child = walkAST(negation.child);
		} else {
			Operator nonTerminal = (Operator) node;
			nonTerminal.leftExpression = walkAST(nonTerminal.leftExpression);
			nonTerminal.rightExpression = walkAST((nonTerminal.rightExpression));
		}
		return node.solve();
	}
}
