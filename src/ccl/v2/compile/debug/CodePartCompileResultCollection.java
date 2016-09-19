package ccl.v2.compile.debug;

import java.util.ArrayList;

import ccl.v2.CclCompileException;

public class CodePartCompileResultCollection extends ArrayList<CodePartCompileResult>{
	
	private static final long serialVersionUID = 2581279388774843814L;

	public String getRaw() throws CclCompileException{
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < size(); i++){
			b.append(get(i).getRaw() + "\n");
		}
		return b.toString();
	}
	
}
