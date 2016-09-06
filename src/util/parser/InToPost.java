package util.parser;

import java.io.IOException;
import java.util.ArrayList;

public class InToPost {
	private ArrayList<String> output = new ArrayList<String>();
	private Stack theStack;
	private String input;

	public InToPost(String in) {
		input = in;
		int stackSize = input.length();
		theStack = new Stack(stackSize);
	}

	// FIXME Tratar negacao dupla
	//FIXME TRABALHAR COM ARRAYLIST, INPUT SERIA UM ARRAY DE STRING...
	public ArrayList<String> doTrans() {
		for (int j = 0; j < input.length(); j++) {
			char ch = input.charAt(j);
			switch (ch) {
			case '>':
				gotOper(ch, 1);
				break;
			case '|':
				gotOper(ch, 2);
				break;
			case '&':
				gotOper(ch, 3);
				break;
			case '~':
				if (String.valueOf(input.charAt(j + 1)).matches("[a-zA-z]")) {
					String chx = new String(String.valueOf(input.charAt(j++)) + String.valueOf(input.charAt(j)));
					output.add(chx);
				} else {
					gotOper(ch, 4);
				}
				break;
			case '(':
				theStack.push(ch);
				break;
			case ')':
				gotParen(ch);
				break;
			default:
				output.add(String.valueOf(ch));
				break;
			}
		}
		while (!theStack.isEmpty()) {
			output.add(String.valueOf(theStack.pop()));
		}
		return output;
	}

	public void gotOper(char opThis, int prec1) {
		while (!theStack.isEmpty()) {
			char opTop = theStack.pop();
			if (opTop == '(') {
				theStack.push(opTop);
				break;
			} else {
				int prec2;
				if (opTop == '>')
					prec2 = 1;
				else if (opTop == '|')
					prec2 = 2;
				else if (opTop == '&')
					prec2 = 3;
				else
					prec2 = 4;
				if (prec2 < prec1) {
					theStack.push(opTop);
					break;
				} else
					output.add(String.valueOf(opTop));
			}
		}
		theStack.push(opThis);
	}

	public void gotParen(char ch) {
		while (!theStack.isEmpty()) {
			char chx = theStack.pop();
			if (chx == '(')
				break;
			else
				output.add(String.valueOf(chx));
		}
	}

	public static void main(String[] args) throws IOException {
		String input = "(P|Q)&~~Q>P";
		ArrayList<String> output;
		InToPost theTrans = new InToPost(input);
		output = theTrans.doTrans();
		System.out.println("Infix is: " + input + '\n');
		System.out.println("Postfix is: " + output + '\n');
	}

	class Stack {
		private int maxSize;
		private char[] stackArray;
		private int top;

		public Stack(int max) {
			maxSize = max;
			stackArray = new char[maxSize];
			top = -1;
		}

		public void push(char j) {
			stackArray[++top] = j;
		}

		public char pop() {
			return stackArray[top--];
		}

		public char peek() {
			return stackArray[top];
		}

		public boolean isEmpty() {
			return (top == -1);
		}
	}
}