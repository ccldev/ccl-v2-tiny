package net.bplaced.opl.ccl;

import java.io.IOException;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public interface CompileSystem<I, O> {
	
	boolean accept(I infos);
	String compileComplete(I infos) throws ImplementationException, DebugException, IOException;
	O getOutput();
	String include();
	
}
