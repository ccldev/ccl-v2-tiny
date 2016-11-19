package ccl.v2_1.pre;

import java.io.IOException;

import net.bplaced.opl.ccl.CompileSystems;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public class PreProcessor {

	private StringBuilder builder = new StringBuilder();

	public void process(String nextLine) throws DebugException, ImplementationException, IOException {
		nextLine = nextLine.trim();
		if(nextLine.startsWith("//")){
			
		}else if(nextLine.startsWith("#")){
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
