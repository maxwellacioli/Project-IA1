package util.grammar;

public class Implication extends NonTerminal {

	@Override
	public LogicalExpression interpret() {

		return null;
	}

	public void solveImplication() {
		Disjunction disj = new Disjunction();
		Negation neg = new Negation();

		neg.setChild(getLeftExpression());

		disj.setFather(getFather());
		disj.setLeftExpression(neg);
		disj.setRightExpression(getRightExpression());
		
		if(getFather() instanceof NonTerminal) {
			//Erro: NonTerminal cannot be resolved to a variable...
			(NonTerminal) getFather().
		}
	}
}
