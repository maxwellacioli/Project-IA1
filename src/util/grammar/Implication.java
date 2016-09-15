package util.grammar;

public class Implication extends NonTerminal {

	@Override
	public LogicalExpression solve() {
		Negation neg = new Negation();

		neg.setChild(getLeftExpression());

		Disjunction disj = new Disjunction();
		disj.setLeftExpression(neg);
		disj.setRightExpression(getRightExpression());
	
		return walkAST(disj);
	}
}
