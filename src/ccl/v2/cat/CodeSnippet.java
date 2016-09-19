package ccl.v2.cat;

import java.io.OutputStream;

import ccl.v2.cpl.CompileOut;
import ccl.v2.sys.CompileSystems;

public class CodeSnippet implements CompileOut {

	private String raw;

	public CodeSnippet(String raw) {
		this.raw = raw;
		System.out.println(raw + "\n---");
	}
	
	@Override
	public String compiled() {
		return CompileSystems.use(raw);
	}

	@Override
	public OutputStream out() {
		throw new RuntimeException("NI");
	}

}
