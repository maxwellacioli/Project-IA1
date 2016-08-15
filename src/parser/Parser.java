package parser;

import java.util.StringTokenizer;

public class Parser {
	private String filePath;

	public Parser(String filePath) {
		this.filePath = filePath;
	}

	public void test() {
		// String input = new String("{(P v Q), P, ~H} |= P");
		String input = new String("(P v Q) ^ Q");

		// (P v Q ^ (Q ^ P), ^, ~H, >, P
		// TODO Criar AST*****

		// Converte a entrada
		input = convertInput(input);

		StringTokenizer st = new StringTokenizer(input);

		// printTokens(st);
	}

	// Imprime todos os tokens
	public void printTokens(StringTokenizer str) {
		while (str.hasMoreTokens()) {
			System.out.println(str.nextToken());
		}
	}

	// Converte a entrada "aplicando a forma normal"
	public String convertInput(String input) {

		input = input.replace("{", "(");
		input = input.replace("}", ")");
		input = input.replace("(", " ( ");
		input = input.replace(")", " ) ");
		input = input.replace(",", " ^ ");
		input = input.replace("|=", " > ");
		input = input.replaceAll("\\s+", " ");

		System.out.println(input);

		return input;
	}

}
