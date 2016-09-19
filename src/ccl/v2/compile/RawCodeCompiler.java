package ccl.v2.compile;

import ccl.v2.CclCompileException;
import ccl.v2.compile.base.CodeCompilerBase;
import ccl.v2.compile.categorize.CodePartDescription;

public class RawCodeCompiler {
	
	public RawCodeCompiler() {}
	
	public String compile(String codePart) throws CclCompileException {
		CodePartDescription description = CodePartDescription.make(codePart);
		if(description.isBlock()){
			return CodeCompilerBase.compileBlock(description.makeBlock());
		}else if(description.isSnippet()){
			return CodeCompilerBase.compileSnippet(description.makeSnippet());
		}
		throw new CclCompileException("Unable to compile code part (unexpected description)!");
	}

}
