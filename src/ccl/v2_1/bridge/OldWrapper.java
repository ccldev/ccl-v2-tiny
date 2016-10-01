package ccl.v2_1.bridge;

import java.io.File;

import ccl.v2_1.cat.CclCodeSnippet;
import ccl.v2_1.err.ImplementationException;
import ccl.v2_1.err.NI;
import ccl.v2_1.sys.CompileSystem;

public class OldWrapper implements CompileSystem<CclCodeSnippet, File>{

	private OldCompileSystem sys;
	
	public OldWrapper(OldCompileSystem sys){
		this.sys = sys;
	}
	
	@Override
	public boolean accept(CclCodeSnippet infos) {
		return sys.accept(infos.getRaw());
	}

	@Override
	public String compileComplete(CclCodeSnippet infos)
			throws ImplementationException {
		return sys.compile(infos.getRaw());
	}

	@Override
	public File getOutput() {
		return null;
	}

	@Override
	public String include() {
		return "";
	}

}
