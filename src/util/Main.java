package util;

import util.grammar.LogicalExpression;
import util.grammar.Operand;
import util.parser.Lexer;
import util.parser.Parser;

//TODO Implementar DeMorgan na nega��o
//FIXME Nomes de m�todos e terminais confusos
//TODO Criar (Heap/Hash) como tabela de simbolos terminais, 
//para n�o ocorrer de dois terminais serem objtos diferentes
// A v B � igual a: A v B v C
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
	// TODO Ao executar o solve de um no, executar o solve dos filhos deste n�?
	public static void main(String[] args) {

		Lexer lexer = new Lexer(filePath);

		Parser parser = new Parser(lexer);
		parser.buildTree();

		LogicalExpression root = parser.getRoot();

		parser.printAST(root);

		root = root.walkAST(root);
		// System.out.println("DebugStartPoint");
		System.out.println("##### AST After Operations #####");
		parser.printAST(root);

		// FIXME Remover desta classe(Main)
		System.out.println("##### Final Result #####");
		if (root instanceof Operand) {
			Operand terminalRoot = (Operand) root;
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