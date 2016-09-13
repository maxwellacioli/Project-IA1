package util.grammar;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import util.parser.Parser;

public abstract class NonTerminal extends LogicalExpression {
	protected LogicalExpression leftExpression;
	protected LogicalExpression rightExpression;

	public void setLeftExpression(LogicalExpression leftExpression) {
		this.leftExpression = leftExpression;
	}

	public void setRightExpression(LogicalExpression rightExpression) {
		this.rightExpression = rightExpression;
	}

	public LogicalExpression getLeftExpression() {
		return leftExpression;
	}

	public LogicalExpression getRightExpression() {
		return rightExpression;
	}
	
	//FIXME Não está funcionando
	public void setNonTerminalChild(LogicalExpression father, LogicalExpression child, Parser parser) {
		try {
			Field leftChildField = father.getClass().getSuperclass().getDeclaredField("leftExpression");
//			Field rightChildField = getFather().getClass().getDeclaredField("rightExpression");
			
			LogicalExpression leftChild = (LogicalExpression) leftChildField.get(father);
//			LogicalExpression rightChild = (LogicalExpression) rightChildField.get(getFather());
			
			if(child.equals(leftChild)) {
				Method leftMethod = father.getClass().getSuperclass().getDeclaredMethod("setLeftExpression",
						LogicalExpression.class);

				leftMethod.invoke(father, child);
			} else {
				Method rightMethod = father.getClass().getSuperclass().getDeclaredMethod("setRightExpression",
						LogicalExpression.class);
				
				rightMethod.invoke(father, child);
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
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
