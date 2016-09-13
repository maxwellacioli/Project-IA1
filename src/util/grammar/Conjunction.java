package util.grammar;

import util.parser.Parser;

public class Conjunction extends NonTerminal {

	public Conjunction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public LogicalExpression solve() {
		// TODO fazer a operaçao entre os dois filhos de cada nó
		return null;
	}

	public LogicalExpression solve(Parser parser) {

		if (getLeftExpression() instanceof Terminal) {
			falseEquivalence(getLeftExpression(), parser);
		} else if (getRightExpression() instanceof Terminal) {
			falseEquivalence(getRightExpression(), parser);
		} else {

		}

		return null;
	}

	private void falseEquivalence(LogicalExpression terminal, Parser parser) {
		Terminal term = (Terminal) terminal;

		if (term.getBooleanValue().equals(Boolean.FALSE)) {
			Terminal falseValue = new Terminal(Boolean.FALSE);

			if (getFather() != null) {
				setNewChild(getFather(), this, falseValue);
			} else {
				parser.setRoot(falseValue);
			}
		}
	}

}
