package ccl.v2_1.operators;

import ccl.v1.Function;

public interface Operators {
	
	Function<Integer[], Boolean> LESS_THEN = new LessOperator();
	Function<Integer[], Boolean> GREATER_THEN = new GreaterOperator();
	
}
