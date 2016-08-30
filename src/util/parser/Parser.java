package util.parser;

import java.lang.reflect.Parameter;
import java.util.StringTokenizer;

public class Parser {
	private String filePath;

	public Parser(String filePath) {
		this.filePath = filePath;
	}

	public void test() {
		String input = new String("{(P v H), ~H} |= P");
		// String input = new String("(Q > P) > Q ");

		// Converte a entrada
		input = convertInput(input);
		System.out.println(replaceImplicationIntoAString(input));

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

		return input;
	}

	// FIXME apenas uma função de teste
	private String replaceImplicationIntoAString(String sentence) {

		StringBuilder sb = new StringBuilder(sentence);

		for (int i = 0; i < sb.length(); i++) {
			int parenBalan = 0;

			if (sb.charAt(i) == '>') {
				sb.setCharAt(i, 'v');
				if (sb.charAt(i - 2) == ')') {
					parenBalan++;

					int j = i - 3;
					while (parenBalan != 0) {
						if (sb.charAt(j) == '(') {
							parenBalan--;
						} else if (sb.charAt(j) == ')') {
							parenBalan++;
						}
						j--;
					}
					String s = convertInput(sb.substring(0, j) + " ~ " + sb.substring(j + 1, sb.length()));
					StringBuilder aux = new StringBuilder(s);
					sb = aux;
				} else {
					String s = convertInput(sb.substring(0, i - 3) + " ~ " + sb.substring(i - 2, sb.length()));
					StringBuilder aux = new StringBuilder(s);
					sb = aux;
				}
			}
		}

		sentence = sb.toString();

		return sentence;
	}
}
