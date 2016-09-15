package util.grammar;

public class Implication extends NonTerminal {

	@Override
	public LogicalExpression solve() {
		Disjunction disj = new Disjunction();
		Negation neg = new Negation();

		neg.setChild(getLeftExpression());

		disj.setLeftExpression(neg);
		disj.setRightExpression(getRightExpression());
	
		return walkAST(disj);
	}
}
