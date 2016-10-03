package ccl.v2_1.pre;

import java.io.IOException;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;
import ccl.v2_1.sys.CompileSystems;

public class PreProcessor {

	private StringBuilder builder = new StringBuilder();

	public void process(String nextLine) throws DebugException, ImplementationException, IOException {
		nextLine = nextLine.trim();
		if(nextLine.startsWith("#")){
			builder.append(CompileSystems.PRE.get(nextLine).getResult());
		}else{
			builder.append(nextLine);
			builder.append("\n");
		}
	}

	public String get() {
		return builder.toString();
	}

}
