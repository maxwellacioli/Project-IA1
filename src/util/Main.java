package util;

import util.grammar.LogicalExpression;
import util.parser.Lexer;
import util.parser.Parser;

public class Main {
	private static String filePath = "files/input.txt";

	// FIXME Diminuir o uso de codigo na main
	// TODO Ao executar o solve de um no, executar o solve dos filhos deste nó?
	public static void main(String[] args) {

		Lexer lexer = new Lexer(filePath);

		Parser parser = new Parser(lexer);
		parser.buildTree();

		LogicalExpression root = parser.getRoot();

		parser.printAST(root);

		// System.out.println("DebugStartPoint");
		root = root.walkAST(root);
		System.out.println("##### After Operations #####");
		parser.printAST(root);
	}
}