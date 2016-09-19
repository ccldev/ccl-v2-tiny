package ccl.v2.compile.categorize;

public class ArgumentList {

	private String raw;

	public ArgumentList(String raw) {
		this.raw = raw;
		throw new RuntimeException(raw);
	}

	public String getRaw() {
		return raw;
	}

}
