package ccl.v2_1.sys;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public interface CompileSystem<I, O> {
	
	boolean accept(I infos);
	String compileComplete(I infos) throws ImplementationException, DebugException;
	O getOutput();
	String include();
	
}
