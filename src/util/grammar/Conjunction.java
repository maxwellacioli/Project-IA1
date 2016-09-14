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

		// TODO Implementar equivalencias logicas
		// TODO Implementar A ^ A = A
		// FIXME Ordem de utilização de equivalencias logicas
		if (getLeftExpression() instanceof Terminal) {
			booleanConjunctionEquivalence(getLeftExpression(), parser);
		}
		if (getRightExpression() instanceof Terminal) {
			booleanConjunctionEquivalence(getRightExpression(), parser);
		}
		// #########

		if (getLeftExpression() instanceof NonTerminal) {

		} else {

		}

		return null;
	}

	private void booleanConjunctionEquivalence(LogicalExpression terminal, Parser parser) {
		Terminal term = (Terminal) terminal;

		if (term.getBooleanValue().equals(Boolean.FALSE)) {
			Terminal falseValue = new Terminal(Boolean.FALSE);

			if (getFather() != null) {
				setNewChild(getFather(), this, falseValue);
			} else {
				parser.setRoot(falseValue);
			}
		}

		if (term.getBooleanValue().equals(Boolean.TRUE)) {

			if (this.getLeftExpression().equals(terminal)) {
				terminal = getRightExpression();
			} else {
				terminal = getLeftExpression();
			}

			if (getFather() != null) {
				setNewChild(getFather(), this, terminal);
			} else {
				parser.setRoot(terminal);
			}
		}
	}
}
