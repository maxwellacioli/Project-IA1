package util.grammar;

import util.parser.Parser;

public class Conjunction extends NonTerminal {

	public Conjunction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public LogicalExpression solve() {

		return this;
	}

	// public LogicalExpression solve(Parser parser) {
	//
	// // TODO Implementar equivalencias logicas
	// // FIXME Ordem de utilização de equivalencias logicas
	// if (getLeftExpression() instanceof Terminal) {
	// Terminal left = (Terminal) getLeftExpression();
	//
	// booleanConjunctionEquivalence(left, parser);
	// sameTerminal(parser); // só precisa verificar 1x
	// if (left.getValue() != null) {
	// walkPreOrderAST(getRightExpression(), left, parser);
	// }
	// }
	// if (getRightExpression() instanceof Terminal) {
	// Terminal right = (Terminal) getRightExpression();
	//
	// booleanConjunctionEquivalence(right, parser);
	// if (right.getValue() != null) {
	// walkPreOrderAST(getLeftExpression(), right, parser);
	// }
	// }
	// // #########
	//
	// if (getLeftExpression() instanceof NonTerminal) {
	//
	// } else {
	//
	// }
	//
	// return null;
	// }
	//
	// private void sameTerminal(Parser parser) {
	//
	// // FIXME Diminuir complexidade
	// if (getLeftExpression() instanceof Terminal && getRightExpression()
	// instanceof Terminal) {
	// Terminal left = (Terminal) getLeftExpression();
	// Terminal right = (Terminal) getRightExpression();
	//
	// if (left.getValue() != null && right.getValue() != null) {
	// if (left.getValue().equals(right.getValue())) {
	// if (getFather() != null) {
	// setNewChild(getFather(), this, left);
	// } else {
	// parser.setRoot(getLeftExpression());
	// }
	// }
	// }
	// return;
	// }
	// }
	//
	// private void booleanConjunctionEquivalence(LogicalExpression terminal,
	// Parser parser) {
	// Terminal term = (Terminal) terminal;
	//
	// if (term.getBooleanValue() != null) {
	// if (term.getBooleanValue().equals(Boolean.FALSE)) {
	// Terminal falseValue = new Terminal(Boolean.FALSE);
	//
	// if (getFather() != null) {
	// setNewChild(getFather(), this, falseValue);
	// } else {
	// parser.setRoot(falseValue);
	// }
	// }
	//
	// if (term.getBooleanValue().equals(Boolean.TRUE)) {
	//
	// if (this.getLeftExpression().equals(terminal)) {
	// terminal = getRightExpression();
	// } else {
	// terminal = getLeftExpression();
	// }
	//
	// if (getFather() != null) {
	// setNewChild(getFather(), this, terminal);
	// } else {
	// parser.setRoot(terminal);
	// }
	// }
	// }
	// }
	//
	// private void walkPreOrderAST(LogicalExpression node, Terminal terminal,
	// Parser parser) {
	// if (node == null) {
	// return;
	// }
	//
	// if (node instanceof Conjunction) {
	// Conjunction conjunction = (Conjunction) node;
	// walkPreOrderAST(conjunction.getLeftExpression(), terminal, parser);
	// walkPreOrderAST(conjunction.getRightExpression(), terminal, parser);
	// } else {
	// if (node instanceof Terminal) {
	// Terminal term = (Terminal) node;
	// if (term.getValue() != null) {
	// if (term.getValue().equals(terminal.getValue())) {
	// Conjunction termFather = (Conjunction) terminal.getFather();
	//
	// if (termFather.getLeftExpression().equals(terminal)) {
	//
	// if (termFather.getFather() != null) {
	// setNewChild(terminal.getFather().getFather(), terminal.getFather(),
	// termFather.getRightExpression());
	// } else {
	// parser.setRoot(termFather.getRightExpression());
	// }
	// } else {
	// if (termFather.getFather() != null) {
	//
	// setNewChild(terminal.getFather().getFather(), terminal.getFather(),
	// termFather.getLeftExpression());
	// } else {
	// parser.setRoot(termFather.getLeftExpression());
	// }
	// }
	// }
	// }
	// }
	// return;
	// }
	// }
}
