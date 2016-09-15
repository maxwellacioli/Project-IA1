package util.grammar;

public abstract class LogicalExpression {

	public abstract LogicalExpression solve();

	public LogicalExpression walkAST(LogicalExpression node) {
		if (node instanceof Terminal) {
			return node;
		}

		if (node instanceof Negation) {
			Negation negation = (Negation) node;
			negation.child = walkAST(negation.child);
		} else {
			NonTerminal nonTerminal = (NonTerminal) node;
			nonTerminal.leftExpression = walkAST(nonTerminal.leftExpression);
			nonTerminal.rightExpression = walkAST((nonTerminal.rightExpression));
		}
		return node.solve();
	}
}
