package ccl.v2_1.operators;

import ccl.v1.Function;

public class LessOperator implements Function<Integer[], Boolean> {

	@Override
	public Boolean call(Integer[] a) {
		return a[0] < a[1];
	}

}
