package util.parser;

import util.grammar.BiImplication;
import util.grammar.Conjunction;
import util.grammar.Disjunction;
import util.grammar.Implication;
import util.grammar.LogicalExpression;
import util.grammar.LogicalOperator;
import util.grammar.Negation;
import util.grammar.NonTerminal;
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
			setChildrenFather(root);
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
			setChildrenFather(root);
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
			setChildrenFather(root);
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
			setChildrenFather(root);
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
		} else if (currentToken.equals(LogicalOperator.NEGATION.getLogicalOperatorValue())) {
			currentToken = nextToken();
			Negation neg = new Negation();
			factor();
			neg.setChild(root);
			root = neg;
			setChildrenFather(root);
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

	public void printASTStack(LogicalExpression node) {
		if (node == null) {
			return;
		}

		if (node instanceof Terminal) {
			if(((Terminal) node).getBooleanValue()!= null) {
				System.out.println(((Terminal) node).getBooleanValue());
			} else {
				System.out.println(((Terminal) node).getValue());
			}
		} else if (node instanceof Negation) {
			// printVisit(node);
			printASTStack(((Negation) node).getChild());
		} else {
			printASTStack(((NonTerminal) node).getLeftExpression());
			printASTStack(((NonTerminal) node).getRightExpression());
		}
		printVisit(node);
	}

	private void printVisit(LogicalExpression node) {
		if (node instanceof Conjunction) {
			System.out.println("^");
			((Conjunction) node).solve(this);
		} else if (node instanceof Disjunction) {
			System.out.println("v");
		} else if (node instanceof Implication) {
			//FIXME Generalizar 
			System.out.println(">");
			((Implication) node).solve(this);
		} else if (node instanceof Negation) {
			System.out.println("~");
		}
	}

	public LogicalExpression getRoot() {
		return root;
	}
	
	public void setRoot(LogicalExpression root) {
		this.root = root;
	}

	private void setChildrenFather(LogicalExpression node) {
		if (node instanceof Negation) {
			((Negation) node).getChild().setFather(node);
		} else if (node instanceof NonTerminal) {
			((NonTerminal) node).getLeftExpression().setFather(node);
			((NonTerminal) node).getRightExpression().setFather(node);
		}
	}
}
