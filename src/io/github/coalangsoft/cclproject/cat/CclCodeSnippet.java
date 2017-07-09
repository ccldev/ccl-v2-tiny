package io.github.coalangsoft.cclproject.cat;

import java.io.File;
import java.io.IOException;

import io.github.coalangsoft.cclproject.CompileSystems;

import ccl.v2_1.code.CclCodePart;
import ccl.v2_1.compile.CclCompileResult;
import ccl.v2_1.compile.Finisher;
import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public class CclCodeSnippet {

	private CclCodePart codePart;

	public CclCodeSnippet(CclCodePart codePart) {
		this.codePart = codePart;
	}
	
	public String getRaw(){
		return codePart.getRaw();
	}

	public String compile() throws DebugException, ImplementationException, IOException {
		CclCompileResult<File> res = CompileSystems.SNIPPET.get(this);
		return Finisher.finish(res);
	}

}
