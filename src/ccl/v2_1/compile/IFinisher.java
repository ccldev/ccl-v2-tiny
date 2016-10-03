package ccl.v2_1.compile;

import java.io.File;
import java.io.IOException;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public interface IFinisher {

	String finish(CclCompileResult<File> res) throws IOException, DebugException, ImplementationException;
	
}
