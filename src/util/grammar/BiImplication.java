package util.grammar;

public class BiImplication extends Operator {

	@Override
	public LogicalExpression solve() {
		Conjunction conjunction = new Conjunction();
		Implication implicationOne = new Implication();
		Implication implicationTwo = new Implication();

		implicationOne.leftExpression = this.leftExpression;
		implicationOne.rightExpression = this.rightExpression;

		implicationTwo.leftExpression = this.rightExpression;
		implicationTwo.rightExpression = this.leftExpression;

		conjunction.leftExpression = implicationOne;
		conjunction.rightExpression = implicationTwo;

		return walkAST(conjunction);
	}
}
