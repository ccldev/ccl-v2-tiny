package ccl.v2.read;

import ccl.v2.compile.debug.CodePartCompiler;
import ccl.v2.debug.CodeArea;

public class CodePart {
	
	private String code;
	private int startLine;
	private String location;
	
	public CodePart(String code, int startLine, String location){
		this.code = code;
		this.startLine = startLine;
		this.location = location;
	}
	
	public String toString(){
		return code;
	}

	public String getCode() {
		return code;
	}

	public int getStartLine() {
		return startLine;
	}

	public String getLocation() {
		return location;
	}
	
	public CodeArea getCodeArea(){
		return new CodeArea(code, startLine, location);
	}
	
	public CodePartCompiler compiler(){
		return new CodePartCompiler(this);
	}
	
}
