package ccl.v2.read;

import ccl.v2.CclCompileException;

public class CodePartReadState {
	
	private int depth = 0;
	private boolean inString = false;
	private boolean unescape = false;
	private boolean finished = false;
	private boolean unfinished = false;
	private boolean empty = true;
	
	public int getDepth() {
		return depth;
	}
	public boolean isInString() {
		return inString;
	}
	public boolean isUnescape() {
		return unescape;
	}
	public boolean isFinished() {
		return finished;
	}
	public boolean isUnfinished() {
		return unfinished;
	}
	public boolean isEmpty() {
		return empty;
	}
	public boolean isClosed(){
		return finished || unfinished;
	}
	
	public void feed(int next) throws CclCompileException {
		if(isClosed()) throw new CclCompileException("The code part is already closed!");
		if(next == -1){
			unfinished = true;
			return;
		}
		empty = false;
		if(unescape){
			unescape = false;
			return;
		}else{
			feed0((char) next);
		}
	}
	private void feed0(char next) {
		if(inString){
			if(next == '"'){
				inString = false;
			}
			return;
		}
		switch(next){
		case '"': inString = true; break;
		case '{': depth++; break;
		case '}': depth--; finish(); break;
		case ';': finish(); break;
		case '\\': unescape = true; break;
		}
	}
	private void finish() {
		finished = (depth == 0) && !unescape && !unfinished && !inString;
		if(depth < 0) unfinished = true;
	}
	
	@Override
	public String toString() {
		return "CodePartReadState [depth=" + depth + ", inString=" + inString
				+ ", unescape=" + unescape + ", finished=" + finished
				+ ", unfinished=" + unfinished + "]";
	}
	public void unfinish() {
		unfinished = true;
	}
	public void check() throws CclCompileException {
		if(isEmpty()) return;
		if(isUnfinished()){
			throw new CclCompileException("Unfinished code part read state! Description:\n" + this);
		}else if(!isClosed()){
			throw new CclCompileException("Unclosed code part (Compiler error)! Description:\n" + this);
		}
	}
	
}
