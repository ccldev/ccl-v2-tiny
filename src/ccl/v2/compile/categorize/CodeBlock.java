package ccl.v2.compile.categorize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeBlock {

	private static final Pattern BLOCK_PATTERN = Pattern.compile
			("\\s*(.*)\\s*\\{\\s*(.*)\\s*\\}\\s*", Pattern.DOTALL);
	
	private Matcher matcher;
	
	public CodeBlock(String code) {
		matcher = BLOCK_PATTERN.matcher(code);
		matcher.find();
	}
	
	public BlockHeader getHeader(){
		return new BlockHeader(matcher.group(1));
	}
	
	public BlockContent getContent(){
		return new BlockContent(matcher.group(2));
	}

}
