package ccl.v2.compile.debug;

import ccl.v2.CclCompileException;
import ccl.v2.read.CodePart;

public class CodePartCompileResult {

	public static CodePartCompileResult error(Exception e, CodePart codePart) {
		return new CodePartCompileResult().setException(new CclCompileException("Exception in code compilation process! Code area:\n" +	codePart.getCodeArea(), e));
	}
	public static CodePartCompileResult normal(String raw) {
		return new CodePartCompileResult().setRaw(raw);
	}

	private CclCompileException exception;
	private String raw;
	
	public CodePartCompileResult setException(CclCompileException e){
		this.exception = e;
		return this;
	}
	
	private void check() throws CclCompileException{
		if(exception != null) throw exception;
	}
	
	public CodePartCompileResult setRaw(String raw){
		this.raw = raw;
		return this;
	}
	
	public String getRaw() throws CclCompileException{
		check();
		return raw;
	}

}
