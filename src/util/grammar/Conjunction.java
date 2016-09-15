package util.grammar;

import util.parser.Parser;

public class Conjunction extends NonTerminal {

	public Conjunction() {
		// TODO Auto-generated constructor stub
	}

	// TODO No retorno, retornar walkAST("novo nó")
	@Override
	public LogicalExpression solve() {

		// Retorna um terminal false caso um do nós seja um terminal com valor
		// false
		if (leftExpression instanceof Terminal) {
			Terminal left = (Terminal) leftExpression;

			if (left.getBooleanValue() != null && left.getBooleanValue().equals(Boolean.FALSE)) {
				return new Terminal(Boolean.FALSE);
			} else if(left.getBooleanValue() != null && left.getBooleanValue().equals(Boolean.TRUE)) {
				return walkAST(rightExpression);
			}
		}

		if (rightExpression instanceof Terminal) {
			Terminal right = (Terminal) rightExpression;

			if (right.getBooleanValue() != null && right.booleanValue.equals(Boolean.FALSE)) {
				return new Terminal(Boolean.FALSE);
			} else if(right.getBooleanValue() != null && right.booleanValue.equals(Boolean.TRUE)) {
				return walkAST(leftExpression);
			}
		}

		return this;
	}
}
