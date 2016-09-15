package util.grammar;

public class Disjunction extends NonTerminal {

	@Override
	public LogicalExpression solve() {

		// FIXME Apenas teste
		// if (this.getLeftExpression() instanceof Terminal) {
		// Terminal left = (Terminal) this.getLeftExpression();
		// if (left.booleanValue == true) {
		// Terminal trueTerminal = new Terminal(true);
		// return trueTerminal;
		//
		// }
		// }

		return this;
	}
}
