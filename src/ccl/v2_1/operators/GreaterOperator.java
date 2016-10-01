package ccl.v2_1.operators;

import ccl.v1.Function;

public class GreaterOperator implements Function<Integer[], Boolean> {

	@Override
	public Boolean call(Integer[] arg) {
		return arg[0] > arg[1];
	}

}
