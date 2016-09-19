package ccl.v2.cpl;

import ccl.v2.CodePart;
import ccl.v2.cat.CodeBlock;
import ccl.v2.cat.CodeSnippet;

public class CodePartCompiler {
	
	public static CompileOut compile(CodePart code){
		CodePartAnalytics infos = new CodePartAnalytics(code.getRaw());
		if(infos.isBlock()){
			return new CodeBlock(code.getRaw());
		}
		if(infos.isSnippet()){
			return new CodeSnippet(code.getRaw());
		}
		System.err.println(code);
		throw new RuntimeException("Unable to categorize code!");
	}
	
}
