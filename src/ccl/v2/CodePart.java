package ccl.v2;

public class CodePart {

	private String code;

	public CodePart(String string) {
		this.code = string.trim();
	}
	
	public boolean isEmpty(){
		return code.isEmpty();
	}
	
	@Override
	public String toString() {
		return "CodePart [code=" + code + "]";
	}

	public String getRaw() {
		return code;
	}

}
