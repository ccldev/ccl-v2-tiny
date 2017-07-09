package io.github.coalangsoft.cclproject.cat;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.coalangsoft.cclproject.CompileSystems;

import ccl.v2_1.code.CclCodePart;
import ccl.v2_1.compile.CclCompileResult;
import ccl.v2_1.compile.Finisher;
import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public class CclCodeBlock {

	private static final Pattern BASE_PATTERN = Pattern.compile
			("([^\\{\\}]*)\\{(.*)\\}", Pattern.DOTALL);
	private static final Pattern CONDITION_PATTERN = Pattern.compile
			("([^\\(\\)]*)\\((.*)\\)(.*)", Pattern.DOTALL);
	private static final Pattern KEYWORD_PATTERN = Pattern.compile
			("([^\\(\\)]*)", Pattern.DOTALL);
	
	private CclCodePart codePart;

	private String before;
	private String content;
	private String keyword;
	private String condition;
	private String afterCondition;
	private CclCodePart following;
	
	public CclCodeBlock(CclCodePart codePart, CclCodePart following) {
		this.codePart = codePart;
		this.following = following;
		analyze();
	}

	private void analyze() {
		String raw = codePart.getRaw().trim();
		Matcher m = BASE_PATTERN.matcher(raw);
		m.matches();
		this.before = m.group(1).trim();
		this.content = m.group(2).trim();
		m = CONDITION_PATTERN.matcher(before);
		if(m.matches()){
			this.keyword = m.group(1).trim();
			this.condition = m.group(2).trim();
			this.afterCondition = m.group(3).trim();
		}else if((m = KEYWORD_PATTERN.matcher(before)).find()){
			this.keyword = m.group(1).trim();
		}
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
