package ccl.v2.cat;

import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccl.v1.read.InputFactory;
import ccl.v2.CodePart;
import ccl.v2.CodeReader;
import ccl.v2.cpl.BlockSurround;
import ccl.v2.cpl.CodePartCompiler;
import ccl.v2.cpl.CompileOut;

public class CodeBlock implements CompileOut {

	private static long globalCount = 0;
	
	private static final Pattern BLOCK_PATTERN = Pattern.compile
			("(.*)\\s*\\{\\s*(.*)\\s*\\}\\s*", Pattern.DOTALL);
	private static final Pattern KEYWORD_PATTERN = Pattern.compile
			("\\s*([a-zA-Z][a-zA-Z0-9_]*)\\s*\\((.*)\\)\\s*");
	
	private String raw;

	private Matcher matcher;
	
	private String before;
	private String content;
	private String keyword;
	private String condition;
	private BlockSurround blockSurround;

	public CodeBlock(String raw) {
		this.raw = raw;
		this.matcher = BLOCK_PATTERN.matcher(raw);
		matcher.find();
		this.before = matcher.group(1).trim();
		this.content = matcher.group(2).trim();
		initBefore();
	}

	private void initBefore() {
		if(before.isEmpty()) return;
		Matcher m = KEYWORD_PATTERN.matcher(before);
		m.find();
		this.keyword = m.group(1).trim();
		this.condition = m.group(2).trim();
		this.blockSurround = BlockSurround.get(keyword);
	}

	@Override
	public String compiled() {
		globalCount++;
		CodeReader reader = new CodeReader(InputFactory.string(matcher.group(2)));
		StringBuilder builder = new StringBuilder();
		while(true){
			CodePart p = reader.next();
			if(p.isEmpty()) break;
			builder.append(CodePartCompiler.compile(p).compiled() + "\n");
		}
		BlockSurround surround = BlockSurround.get(keyword);
		surround.update(globalCount);
		return surround.getBefore(keyword, condition) + 
				"\n" + builder.toString() + 
				"\n" + surround.getAfter(keyword, condition);
	}
	
	@Override
	public OutputStream out() {
		return null;
	}

}