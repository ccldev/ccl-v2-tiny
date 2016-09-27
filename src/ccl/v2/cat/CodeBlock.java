package ccl.v2.cat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	private long myCount;
	
	
	private static final Pattern BLOCK_PATTERN = Pattern.compile
			("(.*)\\s*\\{\\s*(.*)\\s*\\}\\s*", Pattern.DOTALL);
	private static final Pattern KEYWORD_PATTERN = Pattern.compile
			("\\s*([a-zA-Z][a-zA-Z0-9_]*)\\s*\\((.*)\\)\\s*", Pattern.DOTALL);
	private static final Pattern DEF_PATTERN = Pattern.compile
			("\\s*([a-zA-Z][a-zA-Z0-9_]*)\\s*([a-zA-Z0-9_]*)\\s*\\((.*)\\)\\s*", Pattern.DOTALL);
	
	private Matcher matcher;
	
	private String before;
	private String keyword;
	private String condition;
	private String identifier;

	public CodeBlock(String raw) {
		globalCount++;
		myCount = globalCount;
		this.matcher = BLOCK_PATTERN.matcher(raw);
		matcher.find();
		this.before = matcher.group(1).trim();
		initBefore();
	}

	private void initBefore() {
		if(before.isEmpty()) return;
		Matcher m = DEF_PATTERN.matcher(before);
		if(m.find()){
			this.keyword = m.group(1).trim();
			this.identifier = m.group(2).trim();
			this.condition = m.group(3).trim();
		}else{
			m = KEYWORD_PATTERN.matcher(before);
			m.find();
			System.out.println(m);
			this.keyword = m.group(1).trim();
			this.condition = m.group(2).trim();
		}
	}

	@Override
	public String compiled() {
		boolean compileFailed = false;
		
		CodeReader reader = new CodeReader(InputFactory.string(matcher.group(2)));
		StringBuilder builder = new StringBuilder();
		StringBuilder raw = new StringBuilder();
		while(true){
			CodePart p = reader.next();
			if(p.isEmpty()) break;
			raw.append(p.getRaw() + "\n");
			try{
				builder.append(CodePartCompiler.compile(p).compiled() + "\n");
			}catch(RuntimeException e){
				compileFailed = true;
			}
		}
		BlockSurround surround = BlockSurround.get(keyword);
		if(surround != null){
			surround.update(globalCount);
		}
		if(keyword != null){
			if(keyword.equals("def")){
				return surround.getBefore(keyword, condition, identifier) + 
						"\n" + raw.toString() + 
						"\n" + surround.getAfter(keyword, condition, identifier);
			}
			if(keyword.equals("native")){
				return raw.toString();
			}
		}
		if(compileFailed){
			throw new RuntimeException("Failed to compile!");
		}
		return surround.getBefore(keyword, condition, identifier) + 
				"\n" + builder.toString() + 
				"\n" + surround.getAfter(keyword, condition, identifier);
	}
	
	@Override
	public File out() {
		if(keyword == null) return null;
		if(keyword.equals("def")) return new File("__f" + myCount + "__.cl2");
		return null;
	}
	
	public String include(){
		if(keyword == null) return "";
		if(keyword.equals("def")){
			return "V1::" + identifier.trim() + ":M__f" + myCount + "__.cl0";
		}
		return "";
	}

}