package ccl.v2_1.compile;

public class CclCompileResult<O> {

	private String result;
	private O output;
	private String include;

	public CclCompileResult(String result, O output, String include) {
		this.result = result;
		this.output = output;
		this.include = include;
	}

	public String getResult() {
		return result;
	}

	public O getOutput() {
		return output;
	}

	public String getInclude() {
		return include;
	}

}
