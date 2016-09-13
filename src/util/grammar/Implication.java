package util.grammar;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import util.parser.Parser;

public class Implication extends NonTerminal {

	@Override
	public LogicalExpression solve() {

		return null;
	}

	public void solve(Parser parser) {
		Disjunction disj = new Disjunction();
		Negation neg = new Negation();

		neg.setChild(getLeftExpression());

		disj.setFather(getFather());
		disj.setLeftExpression(neg);
		disj.setRightExpression(getRightExpression());

		try {
			if (getFather() == null) {
				parser.setRoot(disj);
			} else if (getFather() instanceof Negation) {
				Method setChildMethod = getFather().getClass().getSuperclass().getDeclaredMethod("setChild",
						LogicalExpression.class);

				setChildMethod.invoke(getFather(), disj);

			} else if (getFather() instanceof NonTerminal) {
				Field field = getFather().getClass().getSuperclass().getDeclaredField("leftExpression");

				Method leftMethod = getFather().getClass().getSuperclass().getDeclaredMethod("setLeftExpression",
						LogicalExpression.class);
				Method rightMethod = getFather().getClass().getSuperclass().getDeclaredMethod("setRightExpression",
						LogicalExpression.class);

				if (field.get(getFather()) instanceof Implication) {
					leftMethod.invoke(getFather(), disj);
				} else {
					rightMethod.invoke(getFather(), disj);
				}
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
