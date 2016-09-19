package ccl.v2.compile.base;

import ccl.v1.Function;
import ccl.v2.compile.categorize.CodeSnippet;

public interface CompileRoutine extends Function<CodeSnippet, String>{
	
	public String getPattern();
	
}
