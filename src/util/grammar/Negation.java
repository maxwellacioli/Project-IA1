package util.grammar;

public class Negation extends LogicalExpression {

	protected LogicalExpression child;
	
	public Negation() {

	}
	
	public Negation(LogicalExpression child) {
		this.child = child;
	}
	
	public void setChild(LogicalExpression child) {
		this.child = child;
	}
	
	public LogicalExpression getChild() {
		return child;
	}
	
	@Override
	public LogicalExpression solve() {
		if(child instanceof Operand) {
			Operand childOperand = (Operand) child;

			if(childOperand.getBooleanValue() != null) {
				childOperand.changeBooleanValue();
			} else {
				childOperand.changeNotFlag();
			}
			
			return childOperand;
		} 
		
		if(child instanceof Conjunction) {
			Disjunction disjunction = new Disjunction();
			Negation leftNegation = new Negation();
			Negation rightNegation = new Negation();
			
			leftNegation.setChild(((Conjunction) child).getLeftExpression());
			rightNegation.setChild(((Conjunction) child).getRightExpression());
			
			disjunction.setLeftExpression(leftNegation);
			disjunction.setRightExpression(rightNegation);
			
			return walkAST(disjunction);
		}
		
		if(child instanceof Disjunction) {
			Conjunction conjunction = new Conjunction();
			Negation leftNegation = new Negation();
			Negation rightNegation = new Negation();
			
			leftNegation.setChild(((Disjunction) child).getLeftExpression());
			rightNegation.setChild(((Disjunction) child).getRightExpression());
			
			conjunction.setLeftExpression(leftNegation);
			conjunction.setRightExpression(rightNegation);
			
			return walkAST(conjunction);
		}
		
		return this;
	}

}
