package ccl.v2_1.debug;

import ccl.v2_1.code.CclCodePart;
import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;
import ccl.v2_1.err.NI;

public class DebugHelper {

	private String part;

	public DebugHelper(CclCodePart part) {
		this.part = part.getRaw();
	}
	
	public DebugHelper(String part){
		this.part = part;
	}

	public void consume(Exception e) {
		if(e instanceof DebugException){
			throw new RuntimeException("Please check out the following code part:\n" + part, e);
		}
		if(e instanceof NI){
			throw new RuntimeException("A feature you want to use is not implemented yet! Code:\n" + part, e);
		}
		if(e instanceof ImplementationException){
			throw new RuntimeException("Oops, something you used should work without seeing this message.\nPlease notice the developer about it! Code:\n" + part, e);
		}
		consume(new ImplementationException(e));
	}

	public static String getCategorizeInfo() {
		return "Every code part has to end with one of this:\n" + 
				"; - Marks a single command (snippet), like function calls and variable declarations.\n" + 
				"} - Marks ( with '{' ) a block, used in function definitions, loop etc.";
								
	}

}
