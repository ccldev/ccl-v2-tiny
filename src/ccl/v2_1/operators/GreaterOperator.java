package ccl.v2_1.operators;

import io.github.coalangsoft.lib.data.Func;

public class GreaterOperator implements Func<Integer[], Boolean> {

	@Override
	public Boolean call(Integer[] arg) {
		return arg[0] > arg[1];
	}

}
