package ccl.v2.compile.debug;

import ccl.v2.CclCompileException;
import ccl.v2.compile.RawCodeCompiler;
import ccl.v2.read.CodePart;
import ccl.v2.read.CodePartReadResult;
import ccl.v2.read.CodePartReader;

public class CodePartCompiler {

	private CodePart codePart;
	
	public static CodePartCompileResultCollection compileAll(CodePartReader reader) throws CclCompileException{
		CodePartCompileResultCollection collection = new CodePartCompileResultCollection();
		CodePartReadResult res = reader.next();
		collection.add(res.get().compiler().compile());
		while(res.isValid()){
			res = reader.next();
			collection.add(res.get().compiler().compile());
		}
		return collection;
	}

	public CodePartCompiler(CodePart codePart) {
		this.codePart = codePart;
	}
	
	public CodePartCompileResult compile(){
		if(codePart.getCode().trim().isEmpty()) return CodePartCompileResult.normal("");
		RawCodeCompiler rawCompiler = new RawCodeCompiler();
		try {
			String raw = rawCompiler.compile(codePart.getCode());
			return CodePartCompileResult.normal(raw);
		} catch (CclCompileException e) {
			return CodePartCompileResult.error(e, codePart);
		} catch (RuntimeException e){
			return CodePartCompileResult.error(e, codePart);
		}
	}

}
