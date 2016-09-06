package util.parser;

import util.grammar.LogicalOperator;
import util.parser.nodetree.LogicalExpression;

public class Parser {
	private Lexer lexer;
	private String currentToken;
	private LogicalExpression root;
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}
	
	public LogicalExpression build() {
		logicalexpression();
		return root;
	}

	private void logicalexpression() {
		biimplication();
		while(currentToken.equals(LogicalOperator.BIIMPLICATION)) {
			Biimplication biimp = new Biimplication();
		}
		
	}
	
}
