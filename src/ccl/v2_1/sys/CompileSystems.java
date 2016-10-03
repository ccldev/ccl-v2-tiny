package ccl.v2_1.sys;

import java.io.File;

import ccl.v2_1.cat.CclCodeBlock;
import ccl.v2_1.cat.CclCodeSnippet;

public interface CompileSystems {
	
	CompileSystemUser<CclCodeBlock, File> BLOCK = new CompileSystemUser<>();
	CompileSystemUser<CclCodeSnippet, File> SNIPPET = new CompileSystemUser<>();
	CompileSystemUser<String, Void> PRE = new CompileSystemUser<>();
	
}
