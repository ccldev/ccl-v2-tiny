package ccl.v2_1.operators;

import io.github.coalangsoft.lib.data.Func;


public interface Operators {
	
	Func<Integer[], Boolean> LESS_THAN = new LessOperator();
	Func<Integer[], Boolean> GREATER_THAN = new GreaterOperator();
	
}
