package util.grammar;

import java.lang.reflect.Field;

import util.parser.Parser;

public class Conjunction extends NonTerminal {

	public Conjunction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public LogicalExpression solve() {
		// TODO fazer a operaçao entre os dois filhos de cada nó
		return null;
	}

	public LogicalExpression solve(Parser parser) {

		try {
			if (getLeftExpression() instanceof Terminal) {
				// FIXME Criar um metodo, para n repetir 2x o mesmo código
				Field booleanValueField = getLeftExpression().getClass().getDeclaredField("booleanValue");
				booleanValueField.setAccessible(true);

				Field valueField = getLeftExpression().getClass().getDeclaredField("value");
				valueField.setAccessible(true);

				String value = (String) valueField.get(getLeftExpression());
				Boolean booleanValue = (Boolean) booleanValueField.get(getLeftExpression());

				if (booleanValue.equals(Boolean.FALSE)) {
					Terminal falseValue = new Terminal(Boolean.FALSE);

					// FIXME Remover esse procedimentos abaixo e criar um método
					// mais geral

					if (getFather() != null) {
						setNonTerminalChild(getLeftExpression().getFather(), getLeftExpression(), parser);
						System.out.println("flag");
					} else {
						parser.setRoot(falseValue);
					}

				}

			} else if (getRightExpression() instanceof Terminal) {

			} else {

			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
