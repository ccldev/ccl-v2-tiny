package ccl.v2_1.bridge;

import ccl.v2_1.err.ImplementationException;

public interface OldCompileSystem {
	
	boolean accept(String snippet);
	String compile(String snippet) throws ImplementationException;
	
}
