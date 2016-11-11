package ccl.v2_1.operators;

import ccl.v1.Function;

public interface Operators {
	
	Function<Integer[], Boolean> LESS_THAN = new LessOperator();
	Function<Integer[], Boolean> GREATER_THAN = new GreaterOperator();
	
}
