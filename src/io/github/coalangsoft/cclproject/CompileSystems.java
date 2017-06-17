package io.github.coalangsoft.cclproject;

import java.io.File;

import io.github.coalangsoft.cclproject.cat.CclCodeSnippet;
import io.github.coalangsoft.cclproject.cat.CclCodeBlock;


public interface CompileSystems {
	
	CompileSystemUser<CclCodeBlock, File> BLOCK = new CompileSystemUser<>();
	CompileSystemUser<CclCodeSnippet, File> SNIPPET = new CompileSystemUser<>();
	CompileSystemUser<String, Void> PRE = new CompileSystemUser<>();
	
}
