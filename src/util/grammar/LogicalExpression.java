package util.grammar;

public abstract class LogicalExpression {
	//FIXME Consertar controle de acesso deste booleano
	public boolean parentheses = false;
	protected LogicalExpression father = null;
	public abstract LogicalExpression solve();
	
	public void setFather(LogicalExpression father) {
		this.father = father;
	}
	
	public LogicalExpression getFather() {
		return father;
	}
}
