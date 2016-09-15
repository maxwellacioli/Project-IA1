package util.grammar;

public class BiImplication extends NonTerminal {

	//TODO CRIAR CONSTRUTOR COM OS DOIS FILHOS DESSE NÓ
	@Override
	public LogicalExpression solve() {
		Conjunction conjunction = new Conjunction();
		Implication implicationOne = new Implication();
		Implication implicationTwo = new Implication();
		
		
		
		conjunction.leftExpression = implicationOne;
		conjunction.rightExpression = implicationTwo;
		
		return conjunction;
	}

}
