package util.parser;

import util.grammar.BiImplication;
import util.grammar.Conjunction;
import util.grammar.Disjunction;
import util.grammar.Implication;
import util.grammar.LogicalOperator;
import util.grammar.Negation;
import util.grammar.Terminal;
import util.parser.nodetree.LogicalExpression;

public class Parser {
	private Lexer lexer;
	private String currentToken;
	private LogicalExpression root;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
		currentToken = lexer.getStringTokenizer().nextToken();
	}

	public LogicalExpression build() {
		logicalexpression();
		return root;
	}

	private void logicalexpression() {
		biImplication();
		while (currentToken.equals(LogicalOperator.BIIMPLICATION.getLogicalOperatorValue())) {
			currentToken = nextToken(currentToken);
			BiImplication biImp = new BiImplication();
			biImp.setLeftExpression(root);
			biImplication();
			biImp.setRightExpression(root);
			root = biImp;
		}
	}

	private void biImplication() {
		implication();
		while (currentToken.equals(LogicalOperator.IMPLICATION.getLogicalOperatorValue())) {
			currentToken = nextToken(currentToken);
			Implication imp = new Implication();
			imp.setLeftExpression(root);
			implication();
			imp.setRightExpression(root);
			root = imp;
		}
	}

	private void implication() {
		disjunction();
		while (currentToken.equals(LogicalOperator.DISJUNCTION.getLogicalOperatorValue())) {
			currentToken = nextToken(currentToken);
			Disjunction disj = new Disjunction();
			disj.setLeftExpression(root);
			disjunction();
			disj.setRightExpression(root);
			root = disj;
		}
	}

	private void disjunction() {
		conjunction();
		while (currentToken.equals(LogicalOperator.CONJUNCTION.getLogicalOperatorValue())) {
			currentToken = nextToken(currentToken);
			Conjunction conj = new Conjunction();
			conj.setLeftExpression(root);
			conjunction();
			conj.setRightExpression(root);
			root = conj;
		}
	}

	// FIXME Operador Unario.... como resolver?
	private void conjunction() {
		negation();
		while (currentToken.equals(LogicalOperator.NEGATION.getLogicalOperatorValue())) {
			currentToken = nextToken(currentToken);
			Conjunction conj = new Conjunction();
			conj.setLeftExpression(root);
			conjunction();
			conj.setRightExpression(root);
			root = conj;
		}
	}

	private void negation() {
		parentheses();
		while (currentToken.equals(LogicalOperator.NEGATION.getLogicalOperatorValue())) {
			currentToken = nextToken(currentToken);
			Negation neg = new Negation();
			terminal();
			neg.setChild(root);
		}
	}

	private void parentheses() {
		if (currentToken.equals("(")) {
			currentToken = nextToken(currentToken);
			logicalexpression();
			try {
				if (currentToken.equals(")")) {
					currentToken = nextToken(currentToken);
				} else {
					throw new ParserErrorException();
				}
			} catch (ParserErrorException e) {
				System.err.println("Missing close parentheses!");
			}
		} else {
			terminal();
		}
	}

	// TODO Criar classe do terminal
	private void terminal() {
		Terminal term;
		if (currentToken.matches("[A-Z]")) {
			term = new Terminal(currentToken);
		} else {
			term = new Terminal(Boolean.valueOf(currentToken));
		}
		root = term;
		currentToken = nextToken(currentToken);
	}

	// FIXME Verificar necessidade do retorno null
	private String nextToken(String currentToken) {
		if (lexer.getStringTokenizer().hasMoreTokens()) {
			return lexer.getStringTokenizer().nextToken();
		} else {
			return "";
		}
	}
}
