package util.parser;

import util.grammar.Biimplication;
import util.grammar.Conjunction;
import util.grammar.Disjunction;
import util.grammar.Implication;
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
			biimp.setLeftExpression(root);
			biimplication();
			biimp.setRightExpression(root);
			root = biimp;
		}
	}

	private void biimplication() {
		implication();
		while(currentToken.equals(LogicalOperator.IMPLICATION)) {
			Implication imp = new Implication();
			imp.setLeftExpression(root);
			implication();
			imp.setRightExpression(root);
			root = imp;
		}
	}

	private void implication() {
		disjunction();
		while(currentToken.equals(LogicalOperator.DISJUNCTION)) {
			Disjunction disj = new Disjunction();
			disj.setLeftExpression(root);
			disjunction();
			disj.setRightExpression(root);
			root = disj;
		}
	}

	private void disjunction() {
		conjunction();
		while(currentToken.equals(LogicalOperator.CONJUNCTION)) {
			Conjunction conj = new Conjunction();
			conj.setLeftExpression(root);
			conjunction();
			conj.setRightExpression(root);
			root = conj;
		}
	}

	//FIXME Operador Unario.... como resolver?
	private void conjunction() {
		negation();
		while(currentToken.equals(LogicalOperator.NEGATION)) {
			Conjunction conj = new Conjunction();
			conj.setLeftExpression(root);
			conjunction();
			conj.setRightExpression(root);
			root = conj;
		}
	}

	//FIXME Verificar a necessidade desse método
	private void negation() {
		// TODO Auto-generated method stub
		
	}

	
}
