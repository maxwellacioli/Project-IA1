package util.grammar;

import util.parser.Parser;

public class Implication extends NonTerminal {

	@Override
	public LogicalExpression solve() {

		return this;
	}

	public void solve(Parser parser) {
		Disjunction disj = new Disjunction();
		Negation neg = new Negation();

		neg.setChild(getLeftExpression());

		disj.setFather(getFather());
		disj.setLeftExpression(neg);
		disj.setRightExpression(getRightExpression());

		if (getFather() == null) {
			parser.setRoot(disj);
		} else {
			setNewChild(getFather(), this, disj);
		}
	}
}
