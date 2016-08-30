package util.parser;

import java.lang.reflect.Parameter;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private String filePath;

	public Parser(String filePath) {
		this.filePath = filePath;
	}

	public void test() {
		// String input = new String("{(P v H), ~H} |= P");
		String input = new String("(P ^ Q) > Q");

		// FIXME Verificar ordem de uso de simplificações
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

	// FIXME Remover alguns erros de ordem de substituiççao
	private String replaceImplicationIntoAString(String sentence) {

		Pattern pattern = Pattern.compile("[(]\\s*.+?\\s*[)]\\s*[>]\\s*[A-Z]");
		Matcher matcher = pattern.matcher(sentence);

		sentence = appendString(pattern, matcher, sentence);

		// Segunda possibilidade de implicação
		pattern = Pattern.compile("[A-Z]+?\\s*[>]\\s*[A-Z]");
		matcher = pattern.matcher(sentence);

		sentence = appendString(pattern, matcher, sentence);

		return sentence;
	}

	private String appendString(Pattern p, Matcher m, String s) {
		while (m.find()) {
			System.out.println(m.group());
			String sentenceAux = m.group();
			sentenceAux = sentenceAux.replace(">", "v");

			int i, j;
			i = m.start();
			j = m.end();
			s = s.substring(0, i) + " ~ " + sentenceAux + s.substring(j, s.length());

			m = p.matcher(s);
		}

		return s;
	}

}
