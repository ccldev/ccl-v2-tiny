package io.github.coalangsoft.cclproject.cat;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccl.v2_1.layers.LayerState;
import io.github.coalangsoft.cclproject.CompileSystems;

import ccl.v2_1.code.CclCodePart;
import ccl.v2_1.compile.CclCompileResult;
import ccl.v2_1.compile.Finisher;
import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public class CclCodeBlock {

	private static final Pattern BASE_PATTERN = Pattern.compile
			("([^\\{\\}]*)\\{(.*)\\}", Pattern.DOTALL);
	
	private CclCodePart codePart;

	private String before;
	private String content;
	private String keyword;
	private String condition;
	private String afterCondition;
	private CclCodePart following;
	
	public CclCodeBlock(CclCodePart codePart, CclCodePart following) throws ImplementationException {
		this.codePart = codePart;
		this.following = following;
		analyze();
	}

	private void analyze() throws ImplementationException {
		int index = 0;
		String raw = codePart.getRaw().trim();

		//parse keyword
		StringBuilder kw = new StringBuilder();
		while((raw.charAt(index) != '{') && (raw.charAt(index) != '(')){
			kw.append(raw.charAt(index));
			index++;
		}
		this.keyword = kw.toString().trim();

		//has condition?
		if(raw.charAt(index) == '('){
			//parse condition
			StringBuilder cnd = new StringBuilder();
			LayerState s = new LayerState(new char[]{'('}, new char[]{')'});
			do{
				s.feed(raw.charAt(index));
				cnd.append(raw.charAt(index));
				index++;
			}while(!s.isBiggest(0));
			this.condition = cnd.substring(1, cnd.length() - 1).trim();

			//parse after condition
			StringBuilder acd = new StringBuilder();
			while(raw.charAt(index) != '{'){
				acd.append(raw.charAt(index));
				index++;
			}
			this.afterCondition = acd.toString().trim();
		}
		parseMainContent(index, raw);
		if(condition == null){
			condition = "";
		}
	}

	private void parseMainContent(int startIndex, String raw) throws ImplementationException {
		this.before = raw.substring(0, startIndex).trim();
		this.content = raw.substring(startIndex);
		this.content = content.substring(1, content.length() - 1).trim();
	}

	public String compile() throws DebugException, ImplementationException, IOException {
		Matcher m = BASE_PATTERN.matcher(codePart.getRaw());
		m.matches();
		CclCompileResult<File> res = CompileSystems.BLOCK.get(this);
		return Finisher.finish(res);
	}
	
	public String compileContent() throws DebugException, ImplementationException, IOException{
		return CclCodePart.compileAll(codePart.buildCodeParts(1));
	}

	@Override
	public String toString() {
		return "CclCodeBlock [codePart=" + codePart + ", before=" + before
				+ ", content=" + content + ", keyword=" + keyword
				+ ", condition=" + condition + "]";
	}

	public String getBefore() {
		return before;
	}
	
	public CclCodePart getFollowing() {
		return following;
	}

	public String getContent() {
		return content;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getCondition() {
		return condition;
	}
	
	public String getAfterCondition() {
		return afterCondition;
	}

	public CclCodePart getCodePart() {
		return codePart;
	}

}
