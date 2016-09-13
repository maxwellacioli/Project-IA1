package util;

import util.parser.Lexer;
import util.parser.Parser;

public class Main {
	private static String filePath = "files/input.txt";

	// FIXME Diminuir o uso de codigo na main
	public static void main(String[] args) {

		Lexer lexer = new Lexer(filePath);

		Parser parser = new Parser(lexer);
		parser.build();
		parser.printASTStack(parser.getRoot());
		System.out.println("##### After Operation #####");
		parser.printASTStack(parser.getRoot());

	}
}