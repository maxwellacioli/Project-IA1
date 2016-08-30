package util;

import util.parser.Parser;

public class Main {
	private static String filePath = "files/input.txt";
	
	public static void main(String[] args) {
		
		Parser parser = new Parser(filePath);
		parser.test();
	}
}