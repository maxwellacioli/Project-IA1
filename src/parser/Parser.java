package parser;

import java.util.StringTokenizer;

public class Parser {
	private String filePath;

	public Parser(String filePath) {
		this.filePath = filePath;
	}

	public void test() {
		// StringTokenizer st = new StringTokenizer("{(P v Q), ~H} |= P");
		StringTokenizer st = new StringTokenizer("(P ^ Q) v P");

		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
