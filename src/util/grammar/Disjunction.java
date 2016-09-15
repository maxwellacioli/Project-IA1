package util.grammar;

public class Disjunction extends Operator {

	public LogicalExpression solve() {

		if (leftExpression instanceof Operand) {
			Operand left = (Operand) leftExpression;

			if (left.getBooleanValue() != null && left.getBooleanValue().equals(Boolean.FALSE)) {
				return walkAST(rightExpression);
			} else if (left.getBooleanValue() != null && left.getBooleanValue().equals(Boolean.TRUE)) {
				return new Operand(Boolean.TRUE);
			} else if (left.getValue() != null && rightExpression instanceof Operand) {
				Operand right = (Operand) rightExpression;
				if (right.getValue() != null && left.getValue().equals(right.getValue())) {
					if(right.getNotFlag() ^ left.getNotFlag()) {
						return new Operand(Boolean.TRUE);
					}
					return right;
				}
			} else if (left.getValue() != null && rightExpression instanceof Disjunction) {
				Disjunction right = (Disjunction) rightExpression;

				Operand equalOperand = walkDisjunctionTree(left.getValue(), right);

				if (equalOperand != null) {
					if (left.getNotFlag() ^ equalOperand.getNotFlag()) {
						return new Operand(Boolean.TRUE);
					}
					return right;
				}
			}
		}

		if (rightExpression instanceof Operand) {
			Operand right = (Operand) rightExpression;

			if (right.getBooleanValue() != null && right.booleanValue.equals(Boolean.FALSE)) {
				return walkAST(leftExpression);
			} else if (right.getBooleanValue() != null && right.booleanValue.equals(Boolean.TRUE)) {
				return new Operand(Boolean.TRUE);
			} else if (right.getValue() != null && leftExpression instanceof Operand) {
				Operand left = (Operand) leftExpression;
				if (left.getValue() != null && right.getValue().equals(left.getValue())) {
					if(right.getNotFlag() ^ left.getNotFlag()) {
						return new Operand(Boolean.TRUE);
					}
					return left;
				}
			} else if (right.getValue() != null && leftExpression instanceof Disjunction) {
				Disjunction left = (Disjunction) leftExpression;

				Operand equalOperand = walkDisjunctionTree(right.getValue(), left);

				if (equalOperand != null) {
					if (right.getNotFlag() ^ equalOperand.getNotFlag()) {
						return new Operand(Boolean.TRUE);
					}
					return left;
				}
			}
		}

		return this;
	}

	private Operand walkDisjunctionTree(String operandValue, Disjunction disjunctionOperator) {
		if (disjunctionOperator.getLeftExpression() instanceof Operand) {
			Operand leftOperand = (Operand) disjunctionOperator.getLeftExpression();

			if (leftOperand.getValue().equals(operandValue)) {
				return leftOperand;
			}
		}

		if (disjunctionOperator.getRightExpression() instanceof Operand) {
			Operand rightOperand = (Operand) disjunctionOperator.getRightExpression();

			if (rightOperand.getValue().equals(operandValue)) {
				return rightOperand;
			}
		}

		if (disjunctionOperator.getLeftExpression() instanceof Disjunction) {
			Disjunction disjunction = (Disjunction) disjunctionOperator.getLeftExpression();
			walkDisjunctionTree(operandValue, disjunction);
		}
		if (disjunctionOperator.getRightExpression() instanceof Disjunction) {
			Disjunction disjunction = (Disjunction) disjunctionOperator.getRightExpression();
			walkDisjunctionTree(operandValue, disjunction);
		}

		return null;
	}
}
