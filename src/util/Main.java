package util;

import util.parser.Lexer;

public class Main {
	private static String filePath = "files/input.txt";
	
	public static void main(String[] args) {
		
		Lexer lexer = new Lexer(filePath);
		lexer.printTokens();
	}
}