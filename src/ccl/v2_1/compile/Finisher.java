package ccl.v2_1.compile;

import java.io.File;
import java.io.IOException;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public class Finisher {
	
	private static IFinisher finisher;
	
	public static String finish(CclCompileResult<File> res) throws IOException, DebugException, ImplementationException{
		return finisher.finish(res);
	}

	public static void set(IFinisher finisherImpl) {
		finisher = finisherImpl;
	}
	
}
