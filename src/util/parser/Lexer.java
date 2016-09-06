package util.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
	private String filePath;
	private String sentence;
	private BufferedReader br;
	private StringTokenizer st;

	public Lexer(String filePath) {
		this.filePath = filePath;

		readFile();

		sentence = convertInput(sentence);

		st = new StringTokenizer(sentence);
	}

	public void readFile() {

		try {
			br = new BufferedReader(new FileReader(filePath));

			sentence = br.readLine();

			if (sentence == null) {
				throw new NullPointerException();
			}

			br.close();
		} catch (NullPointerException e) {
			System.out.println("Arquivo vazio!");
		} catch (IOException e) {
			System.out.println("Arquivo não lido");
		}
	}

	// Imprime todos os tokens
	public void printTokens() {
		while (st.hasMoreTokens()) {
			System.out.print(st.nextToken());
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

	// // FIXME Remover alguns erros de ordem de substituiççao
	// private String replaceImplicationIntoAString(String sentence) {
	//
	// Pattern pattern = Pattern.compile("[(]\\s*.+?\\s*[)]\\s*[>]\\s*[A-Z]");
	// Matcher matcher = pattern.matcher(sentence);
	//
	// sentence = appendString(pattern, matcher, sentence);
	//
	// // Segunda possibilidade de implicação
	// pattern = Pattern.compile("[A-Z]+?\\s*[>]\\s*[A-Z]");
	// matcher = pattern.matcher(sentence);
	//
	// sentence = appendString(pattern, matcher, sentence);
	//
	// return sentence;
	// }
	//
	// // FIXME Método teste
	// private String appendString(Pattern p, Matcher m, String s) {
	// while (m.find()) {
	// System.out.println(m.group());
	// String sentenceAux = m.group();
	// sentenceAux = sentenceAux.replace(">", "v");
	//
	// int i, j;
	// i = m.start();
	// j = m.end();
	// s = s.substring(0, i) + " ~ " + sentenceAux + s.substring(j, s.length());
	//
	// m = p.matcher(s);
	// }
	//
	// return s;
	// }

}
