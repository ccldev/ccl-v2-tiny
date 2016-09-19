package ccl.v2.compile.categorize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockHeader {

	private static final Pattern HEADER_PATTERN = Pattern.compile
			("\\s*([[a-zA-Z0-9]*|])\\s*(\\s*\\(.*)\\s*\\)\\s*", Pattern.DOTALL);
	
	private String raw;
	private Matcher matcher;

	public BlockHeader(String raw) {
		this.raw = raw;
		this.matcher = HEADER_PATTERN.matcher(raw);
		matcher.find();
	}

	public String getRaw() {
		return raw;
	}
	
	public boolean hasArguments(){
		return !matcher.group(1).isEmpty();
	}
	
	public boolean hasKeyword(){
		return !getKeyword().isEmpty();
	}
	
	public String getKeyword(){
		return matcher.group(2);
	}
	
	public ArgumentList getArguments(){
		return new ArgumentList(matcher.group(1));
	}

}
