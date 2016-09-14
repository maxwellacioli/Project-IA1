package util.grammar;

public class Disjunction extends NonTerminal {

	@Override
	public LogicalExpression solve() {

		// FIXME Apenas teste
		Terminal left = null;
		Terminal right = null;
		if (getLeftExpression() instanceof Terminal)
			left = (Terminal) getLeftExpression();
		if (getRightExpression() instanceof Terminal)
			right = (Terminal) getRightExpression();

		if (left.booleanValue == true || right.booleanValue == true) {
			Terminal trueTerminal = new Terminal(true);
			return trueTerminal;
		}
		return this;
	}
}
