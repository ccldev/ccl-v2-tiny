package ccl.v2.debug;

public class CodeArea {

	private String code;
	private int startLine;
	private String location;

	public CodeArea(String code, int startLine, String location) {
		this.code = code;
		this.startLine = startLine;
		this.location = location;
	}
	
	public LineArea getLineArea(){
		return new LineArea(startLine, code.length() - code.replace("\n", "").length());
	}
	
	public String toString(){
		return location + "; " + getLineArea() + "; Code:\n" + code + "\n";
	}

}
