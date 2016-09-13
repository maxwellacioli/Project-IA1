package util.grammar;

public class Negation extends LogicalExpression {

	protected LogicalExpression child;
	
	public void setChild(LogicalExpression child) {
		this.child = child;
	}
	
	public LogicalExpression getChild() {
		return child;
	}
	
	@Override
	public LogicalExpression solve() {
		// TODO Auto-generated method stub
		return null;
	}

}
