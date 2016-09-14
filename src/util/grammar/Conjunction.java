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

	//FIXME Resolver A ^ true = A
	public LogicalExpression solve(Parser parser) {
		// TODO Implementar equivalencias logicas
		if (getLeftExpression() instanceof Terminal) {
			Terminal term = (Terminal) getLeftExpression();
			if (term.getBooleanValue() != null) { //Está ocorrando curto circuito, ocasionando nao resolucao da expressao
				booleanConjunctionEquivalence(getLeftExpression(), parser);
			}
		} else if (getRightExpression() instanceof Terminal) {
			Terminal term = (Terminal) getRightExpression();
			if (term.getBooleanValue() != null) {
				booleanConjunctionEquivalence(getRightExpression(), parser);
			}
		} else {

		}

		return null;
	}

	// FIXME Ordem de resolução de equivalencias errada
	private void booleanConjunctionEquivalence(LogicalExpression terminal, Parser parser) {
		Terminal term = (Terminal) terminal;

		// TODO Implementar A ^ A = A
		if (term.getBooleanValue().equals(Boolean.FALSE)) {
			Terminal falseValue = new Terminal(Boolean.FALSE);

			if (getFather() != null) {
				setNewChild(getFather(), this, falseValue);
			} else {
				parser.setRoot(falseValue);
			}
		} else if (term.getBooleanValue().equals(Boolean.TRUE)) {

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
