package ccl.v2_1.bridge;

import java.io.File;

import net.bplaced.opl.ccl.CompileSystem;
import net.bplaced.opl.ccl.cat.CclCodeSnippet;

import ccl.v2_1.err.ImplementationException;

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
