package util.grammar;

public class Conjunction extends NonTerminal {

	// TODO Implementar o caso de notTerminal flag com valor true
	@Override
	public LogicalExpression solve() {

		if (leftExpression instanceof Terminal) {
			Terminal left = (Terminal) leftExpression;

			if (left.getBooleanValue() != null && left.getBooleanValue().equals(Boolean.FALSE)) {
				return new Terminal(Boolean.FALSE);
			} else if (left.getBooleanValue() != null && left.getBooleanValue().equals(Boolean.TRUE)) {
				return walkAST(rightExpression);
			} else if (left.getValue() != null && rightExpression instanceof Terminal) {
				Terminal right = (Terminal) rightExpression;
				if (right.getValue() != null && left.getValue().equals(right.getValue())) {
					return new Terminal(right.getValue());
				}
			}
		}

		if (rightExpression instanceof Terminal) {
			Terminal right = (Terminal) rightExpression;

			if (right.getBooleanValue() != null && right.booleanValue.equals(Boolean.FALSE)) {
				return new Terminal(Boolean.FALSE);
			} else if (right.getBooleanValue() != null && right.booleanValue.equals(Boolean.TRUE)) {
				return walkAST(leftExpression);
			} else if (right.getValue() != null && leftExpression instanceof Terminal) {
				Terminal left = (Terminal) leftExpression;
				if (left.getValue() != null && right.getValue().equals(left.getValue())) {
					return new Terminal(left.getValue());
				}
			}
		}

		return this;
	}
}
