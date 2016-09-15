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
					if (right.getNotFlag() ^ left.getNotFlag()) {
						return new Operand(Boolean.FALSE);
					}
					return right;
				}
			} else if (left.getValue() != null && rightExpression instanceof Conjunction) {
				Conjunction right = (Conjunction) rightExpression;

				Operand equalOperand = walkConjunctionTree(left.getValue(), right);

				if (equalOperand != null) {
					if (left.getNotFlag() ^ equalOperand.getNotFlag()) {
						return new Operand(Boolean.FALSE);
					}
					return right;
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
					if (right.getNotFlag() ^ left.getNotFlag()) {
						return new Operand(Boolean.FALSE);
					}
					return left;
				}
			} else if (right.getValue() != null && leftExpression instanceof Conjunction) {
				Conjunction left = (Conjunction) leftExpression;

				Operand equalOperand = walkConjunctionTree(right.getValue(), left);

				if (equalOperand != null) {
					if (right.getNotFlag() ^ equalOperand.getNotFlag()) {
						return new Operand(Boolean.FALSE);
					}
					return left;
				}
			}
		}

		return this;
	}

	private Operand walkConjunctionTree(String operandValue, Conjunction conjunctionOperator) {
		if (conjunctionOperator.getLeftExpression() instanceof Operand) {
			Operand leftOperand = (Operand) conjunctionOperator.getLeftExpression();

			if (leftOperand.getValue().equals(operandValue)) {
				return leftOperand;
			}
		}

		if (conjunctionOperator.getRightExpression() instanceof Operand) {
			Operand rightOperand = (Operand) conjunctionOperator.getRightExpression();

			if (rightOperand.getValue().equals(operandValue)) {
				return rightOperand;
			}
		}

		if (conjunctionOperator.getLeftExpression() instanceof Conjunction) {
			Conjunction conjunction = (Conjunction) conjunctionOperator.getLeftExpression();
			walkConjunctionTree(operandValue, conjunction);
		}
		if (conjunctionOperator.getRightExpression() instanceof Conjunction) {
			Conjunction conjunction = (Conjunction) conjunctionOperator.getRightExpression();
			walkConjunctionTree(operandValue, conjunction);
		}

		return null;
	}
}
