package ccl.v2_1.code;

import java.io.IOException;

import net.bplaced.opl.ccl.cat.CclCodeBlock;
import net.bplaced.opl.ccl.cat.CclCodeSnippet;

import ccl.v2_1.debug.DebugHelper;
import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public class CclCodePart extends CclCode {

	public CclCodePart(String code) throws ImplementationException, DebugException {
		super(code);
	}
	
	public boolean isBlock(){
		return getRaw().endsWith("}");
	}
	
	public boolean isSnippet(){
		return getRaw().endsWith(";");
	}
	
	public String compile(CclCodePart following) throws DebugException, ImplementationException, IOException{
		if(isEmpty()) return "";
		if(isBlock()){
			return new CclCodeBlock(this, following).compile();
		}else if(isSnippet()){
			return new CclCodeSnippet(this).compile();
		}else throw new DebugException("Unable to categorize code! \n" + this.getRaw() + "\n---\n" + DebugHelper.getCategorizeInfo());
	}

	private boolean isEmpty() {
		return getRaw().trim().isEmpty();
	}

	public static String compileAll(CclCodePart... parts) throws DebugException, ImplementationException, IOException {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < parts.length; i++){
			builder.append(parts[i].compile(i + 1 < parts.length ? parts[i+1] : null));
			builder.append("\n");
		}
		return builder.toString();
	}

	@Override
	public String toString() {
		return getRaw();
	}

}
