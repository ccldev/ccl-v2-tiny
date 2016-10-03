package ccl.v2_1.cat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import ccl.v2_1.code.CclCodePart;
import ccl.v2_1.compile.CclCompileResult;
import ccl.v2_1.compile.Finisher;
import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;
import ccl.v2_1.err.NI;
import ccl.v2_1.sys.CompileSystems;

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
