package ccl.v2_1.operators;

import io.github.coalangsoft.lib.data.Func;

public class LessOperator implements Func<Integer[], Boolean> {

	@Override
	public Boolean call(Integer[] a) {
		return a[0] < a[1];
	}

}
