package util.grammar;

public class Conjunction extends Operator {

	// TODO Implementar o caso de notTerminal flag com valor true
	@Override
	public LogicalExpression solve() {

		if (leftExpression instanceof Operand) {
			Operand left = (Operand) leftExpression;

			if (left.getBooleanValue() != null && left.getBooleanValue().equals(Boolean.FALSE)) {
				return new Operand(Boolean.FALSE);
			} else if (left.getBooleanValue() != null && left.getBooleanValue().equals(Boolean.TRUE)) {
				return walkAST(rightExpression);
			} else if (left.getValue() != null && rightExpression instanceof Operand) {
				Operand right = (Operand) rightExpression;
				if (right.getValue() != null && left.getValue().equals(right.getValue())) {
					return new Operand(right.getValue());
				}
			}
		}

		if (rightExpression instanceof Operand) {
			Operand right = (Operand) rightExpression;

			if (right.getBooleanValue() != null && right.booleanValue.equals(Boolean.FALSE)) {
				return new Operand(Boolean.FALSE);
			} else if (right.getBooleanValue() != null && right.booleanValue.equals(Boolean.TRUE)) {
				return walkAST(leftExpression);
			} else if (right.getValue() != null && leftExpression instanceof Operand) {
				Operand left = (Operand) leftExpression;
				if (left.getValue() != null && right.getValue().equals(left.getValue())) {
					return new Operand(left.getValue());
				}
			}
		}

		return this;
	}
	
	private void walkConjunctionTree() {
		
	}
}
