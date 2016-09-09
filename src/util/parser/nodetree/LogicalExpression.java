package util.parser.nodetree;

public abstract class LogicalExpression {
	public boolean parentheses = false;
	public abstract LogicalExpression interpret();
}
