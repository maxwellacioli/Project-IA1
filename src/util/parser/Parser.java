package util.parser;

import util.grammar.BiImplication;
import util.grammar.Conjunction;
import util.grammar.Disjunction;
import util.grammar.Implication;
import util.grammar.LogicalExpression;
import util.grammar.LogicalOperator;
import util.grammar.Negation;
import util.grammar.Operator;
import util.grammar.Operand;

public class Parser {
	private Lexer lexer;
	private String currentToken;
	private LogicalExpression root;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
		currentToken = lexer.getStringTokenizer().nextToken();
	}

	public LogicalExpression buildTree() {
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
				} else {
					throw new ParserErrorException();
				}
			} catch (ParserErrorException e) {
				System.err.println("Missing close parentheses!");
			}
		} else if (currentToken.equals(LogicalOperator.NEGATION.getLogicalOperatorValue())) {
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
		Operand term;
		if (currentToken.matches("[A-Z]")) {
			term = new Operand(currentToken);
		} else {
			term = new Operand(Boolean.valueOf(currentToken));
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

	public void printAST(LogicalExpression node) {
		if (node == null) {
			return;
		}

		if (node instanceof Operand) {
			if (((Operand) node).getBooleanValue() != null) {
				System.out.println(((Operand) node).getBooleanValue());
			} else {
				System.out.println(((Operand) node).getValue());
			}
		} else

		if (node instanceof Negation) {
			printAST(((Negation) node).getChild());
		} else {
			printAST(((Operator) node).getLeftExpression());
			printAST(((Operator) node).getRightExpression());
		}
		visitNode(node);
	}

	private void visitNode(LogicalExpression node) {
		if (node instanceof Conjunction) {
			System.out.println("^");
		} else if (node instanceof Disjunction) {
			System.out.println("v");
		} else if (node instanceof Implication) {
			System.out.println(">");
		} else if (node instanceof Negation) {
			System.out.println("~");
		} else if(node instanceof BiImplication) {
			System.out.println("<>");
		}
	}

	public LogicalExpression getRoot() {
		return root;
	}

	public void setRoot(LogicalExpression root) {
		this.root = root;
	}
}
