package util.parser.nodetree;

public class BinaryOpNode extends Node {

	LogicalExpression leftOperand;
	LogicalExpression rightOperand;

	// FIXME Criar operador
	// public BinaryOpNode(LogicalExpression leftOperand, LogicalExpression
	// rightOperand, Operator operator) {
	public BinaryOpNode(LogicalExpression leftOperand, LogicalExpression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	//TODO passar os metodos que resolvem ambos os subnos de cada nó
	@Override
	public LogicalExpression interpret() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getNodeValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printStackCommands() {
		// TODO Auto-generated method stub

	}

}
