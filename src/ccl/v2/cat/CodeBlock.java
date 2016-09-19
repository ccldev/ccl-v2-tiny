package ccl.v2.cat;

import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccl.v1.read.InputFactory;
import ccl.v2.CodePart;
import ccl.v2.CodeReader;
import ccl.v2.cpl.CodePartCompiler;
import ccl.v2.cpl.CompileOut;

public class CodeBlock implements CompileOut {

	private static final Pattern BLOCK_PATTERN = Pattern.compile
			("(.*)\\s*\\{\\s*(.*)\\s*\\}\\s*", Pattern.DOTALL);
	
	private String raw;

	private Matcher matcher;

	public CodeBlock(String raw) {
		this.raw = raw;
		this.matcher = BLOCK_PATTERN.matcher(raw);
		matcher.find();
	}

	@Override
	public String compiled() {
		CodeReader reader = new CodeReader(InputFactory.string(matcher.group(2)));
		StringBuilder builder = new StringBuilder();
		while(true){
			CodePart p = reader.next();
			if(p.isEmpty()) break;
			builder.append(CodePartCompiler.compile(p).compiled() + "\n");
		}
		return builder.toString();
	}

	@Override
	public OutputStream out() {
		throw new RuntimeException("NI");
	}

}