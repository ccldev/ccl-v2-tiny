package ccl.v2.compile.categorize;

import ccl.v2.CclCompileException;

public class CodePartDescription {

	public CodePartType type;
	private String code;
	
	public CodePartDescription(CodePartType type, String codePart) {
		this.type = type;
		code = codePart;
	}

	public CodePartType getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public static CodePartDescription make(String codePart) throws CclCompileException {
		codePart = codePart.trim();
		if(codePart.endsWith(";")) return new CodePartDescription(CodePartType.SNIPPET, codePart);
		if(codePart.endsWith("}")) return new CodePartDescription(CodePartType.BLOCK, codePart);
		throw new CclCompileException("Unable to categorize code part (compile error)!");
	}

	public boolean isBlock() {
		return type == CodePartType.BLOCK;
	}
	public boolean isSnippet() {
		return type == CodePartType.SNIPPET;
	}

	public CodeBlock makeBlock() throws CclCompileException {
		if(!isBlock()) throw new CclCompileException("This code part is not a block (compile error)!");
		return new CodeBlock(code);
	}

	public CodeSnippet makeSnippet() throws CclCompileException {
		if(!isSnippet()) throw new CclCompileException("This code part is not a snippet (compile error)!");
		return new CodeSnippet(code);
	}

}
