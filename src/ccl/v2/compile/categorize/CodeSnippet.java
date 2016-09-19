package ccl.v2.compile.categorize;

import java.util.regex.Pattern;

import ccl.v1.Function;

public class CodeSnippet {

	private String raw;

	public CodeSnippet(String raw) {
		this.raw = raw;
	}
	
	public boolean matches(String pattern){
		return Pattern.matches(pattern, raw);
	}
	
	public String compile(Function<String, String> compiler){
		return compiler.call(raw);
	}
	
	public String toString(){
		return getRaw();
	}

	public String getRaw() {
		return raw;
	}

}
