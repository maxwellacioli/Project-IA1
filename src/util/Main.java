package util;

import util.grammar.Disjunction;
import util.grammar.LogicalExpression;
import util.grammar.Terminal;
import util.parser.Lexer;
import util.parser.Parser;

//TODO Mudar nomes
//TODO Criar (Heap/Hash) como tabela de simbolos terminais, 
//para não ocorrer de dois terminais serem objtos diferentes
// A v B é igual a: A v B v C
//Enviar projeto para: ebcosta@gmail.com
//Casos de Teste:
//1) {A, B > C, A > B} |= C
//2) {A > (B > C), ~~A, B} |= C
//3)* {(A v B) ^ (A v C), ~A} |= (B ^ C)
//4)* {A > B, (A > B) > (B > A)} |= (A <> B)
//5) {A <> (S ^ D), S ^ D, (S ^ D) > A} |= A
//6) {A > B, ~B ^ C, ~B} |= (C ^ ~A)
//7) {~D > R, D v R, S, A v ~D, S > D, A > X} |= X
//8) {~P v ~T, T > X, ~T, X > T, X v T} |= X

public class Main {
	private static String filePath = "files/input.txt";

	// FIXME Diminuir o uso de codigo na main
	// TODO Ao executar o solve de um no, executar o solve dos filhos deste nó?
	public static void main(String[] args) {

		Lexer lexer = new Lexer(filePath);

		Parser parser = new Parser(lexer);
		parser.buildTree();

		LogicalExpression root = parser.getRoot();

		parser.printAST(root);

		// System.out.println("DebugStartPoint");
		root = root.walkAST(root);
		System.out.println("##### AST After Operations #####");
		parser.printAST(root);

		// FIXME Remover desta classe(Main)
		System.out.println("##### Final Result #####");
		if (root instanceof Terminal) {
			Terminal terminalRoot = (Terminal) root;
			if (terminalRoot.getBooleanValue() != null && terminalRoot.getBooleanValue().equals(Boolean.TRUE)) {
				System.out.println("Tautology");
			} else {
				System.out.println("Not Tautology");
			}
		} else {
			System.out.println("Not Tautology");
		}
	}
}