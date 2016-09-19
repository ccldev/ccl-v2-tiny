package ccl.v2.cpl;

public class CodePartAnalytics {

	private String code;

	public CodePartAnalytics(String code) {
		this.code = code;
	}
	
	public boolean isBlock(){
		return code.endsWith("}");
	}
	public boolean isSnippet(){
		return code.endsWith(";");
	}
	
}
