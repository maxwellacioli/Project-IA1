package util.grammar;

public class Conjunction extends NonTerminal {

	public Conjunction() {
		// TODO Auto-generated constructor stub
	}
	
	public Conjunction(LogicalExpression leftExpression, LogicalExpression rightExpression) {
		super.setLeftExpression(leftExpression);
		super.setRightExpression(rightExpression);
	}
	
	@Override
	public LogicalExpression interpret() {
		//TODO fazer a opera�ao entre os dois filhos de cada n�
		//return leftExpression.interpret() && rightExpression.interpret();
		return null;
	}
	
}
