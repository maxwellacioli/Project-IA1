package util.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Lexer {
	private String filePath;
	private String sentence;
	private BufferedReader br;
	private StringTokenizer st;

	//FIXME Criar array com setenças para A e B em (A |= B)
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
			System.err.println("Arquivo vazio!");
		} catch (IOException e) {
			System.err.println("Arquivo não lido");
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
		input = input.replace("<>", " <> ");
		input = input.replace(">", " > ");
		input = input.replace("v", " v ");
		input = input.replace("^", " ^ ");
		input = input.replace("~", " ~ ");
		input = input.replaceAll("\\s+", " ");

		return input;
	}
	
	public StringTokenizer getStringTokenizer(){
		return st;
	}
}
