package net.bplaced.opl.ccl;

import java.io.File;

import net.bplaced.opl.ccl.cat.CclCodeBlock;
import net.bplaced.opl.ccl.cat.CclCodeSnippet;


public interface CompileSystems {
	
	CompileSystemUser<CclCodeBlock, File> BLOCK = new CompileSystemUser<>();
	CompileSystemUser<CclCodeSnippet, File> SNIPPET = new CompileSystemUser<>();
	CompileSystemUser<String, Void> PRE = new CompileSystemUser<>();
	
}
