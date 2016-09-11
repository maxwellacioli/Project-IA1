package util.parser;

import util.grammar.BiImplication;
import util.grammar.Conjunction;
import util.grammar.Disjunction;
import util.grammar.Implication;
import util.grammar.LogicalExpression;
import util.grammar.LogicalOperator;
import util.grammar.Negation;
import util.grammar.Terminal;

public class Parser {
	private Lexer lexer;
	private String currentToken;
	private LogicalExpression root;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
		currentToken = lexer.getStringTokenizer().nextToken();
	}

	public LogicalExpression build() {
		biImplication();
		return root;
	}

	private void biImplication() {
		implication();
		while (currentToken.equals(LogicalOperator.BIIMPLICATION.getLogicalOperatorValue())) {
			currentToken = nextToken();
			BiImplication biImp = new BiImplication();
			biImp.setLeftExpression(root);
			implication();
			biImp.setRightExpression(root);
			root = biImp;
		}
	}

	private void implication() {
		disjunction();
		while (currentToken.equals(LogicalOperator.IMPLICATION.getLogicalOperatorValue())) {
			currentToken = nextToken();
			Implication imp = new Implication();
			imp.setLeftExpression(root);
			disjunction();
			imp.setRightExpression(root);
			root = imp;
		}
	}

	private void disjunction() {
		conjunction();
		while (currentToken.equals(LogicalOperator.DISJUNCTION.getLogicalOperatorValue())) {
			currentToken = nextToken();
			Disjunction disj = new Disjunction();
			disj.setLeftExpression(root);
			conjunction();
			disj.setRightExpression(root);
			root = disj;
		}
	}

	private void conjunction() {
		factor();
		while (currentToken.equals(LogicalOperator.CONJUNCTION.getLogicalOperatorValue())) {
			currentToken = nextToken();
			Conjunction conj = new Conjunction();
			conj.setLeftExpression(root);
			factor();
			conj.setRightExpression(root);
			root = conj;
		}
	}

	private void factor() {
		if (currentToken.equals("(")) {
			currentToken = nextToken();
			biImplication();
			try {
				if (currentToken.equals(")")) {
					currentToken = nextToken();
					root.parentheses = true;
				} else {
					throw new ParserErrorException();
				}
			} catch (ParserErrorException e) {
				System.err.println("Missing close parentheses!");
			}
		} else if(currentToken.equals(LogicalOperator.NEGATION.getLogicalOperatorValue())){
			currentToken = nextToken();
			Negation neg = new Negation();
			factor();
			neg.setChild(root);
			root = neg;
		} else {
			terminal();
		}
	}

	private void terminal() {
		Terminal term;
		if (currentToken.matches("[A-Z]")) {
			term = new Terminal(currentToken);
		} else {
			term = new Terminal(Boolean.valueOf(currentToken));
		}
		root = term;
		currentToken = nextToken();
	}

	private String nextToken() {
		if (lexer.getStringTokenizer().hasMoreTokens()) {
			return lexer.getStringTokenizer().nextToken();
		} else {
			return "";
		}
	}

	// TODO percorrer a arvore...
	public void printAST(LogicalExpression node) {
		if(node == null) {
			return;
		} else if(node instanceof Terminal) {
			System.out.println(node.toString());
		}
	}
}
