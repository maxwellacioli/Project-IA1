package util.parser.nodetree;

public abstract class UnaryOpNode implements LogicalExpression {

	public abstract Node getNodeValue();

	public abstract void printStackCommands();

}
