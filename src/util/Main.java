package util;

import util.parser.Lexer;
import util.parser.Parser;

public class Main {
	private static String filePath = "files/input.txt";
	
	public static void main(String[] args) {
		
		Lexer lexer = new Lexer(filePath);
		
		Parser parser = new Parser(lexer);
		parser.build();
		parser.printAST(parser.getRoot());
	}
}