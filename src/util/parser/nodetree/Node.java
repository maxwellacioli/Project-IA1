package util.parser.nodetree;

public abstract class Node implements LogicalExpression {
	
	//FIXME Verificar valor a esquerda e valor a direita
	public abstract Node getNodeValue();
	
	public abstract void printStackCommands();
	
}

