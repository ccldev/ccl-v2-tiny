package ccl.v2_1.bridge;

import java.io.File;

import net.bplaced.opl.ccl.CompileSystem;
import net.bplaced.opl.ccl.cat.CclCodeBlock;

import ccl.v2_1.err.ImplementationException;

public abstract class BlockSurround implements CompileSystem<CclCodeBlock, File>{

	@Override
	public boolean accept(CclCodeBlock infos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String compileComplete(CclCodeBlock infos)
			throws ImplementationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String include() {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract String getBefore(String keyword, String condition, String identifier) throws ImplementationException;
	public abstract String getAfter(String keyword, String condition, String identifier) throws ImplementationException;

}
