package ccl.v2.read;

import ccl.v2.CclCompileException;

public class CodePartReadResult {

	private StringBuilder content;
	private CodePartReadState state;
	private String location;
	private int startLine;
	
	public CodePartReadResult(String location, int startLine){
		content = new StringBuilder();
		state = new CodePartReadState();
		this.location = location;
		this.startLine = startLine;
	}
	
	public boolean feed(int next) throws CclCompileException {
		if(state.isClosed()) throw new CclCompileException("The code part is already closed!");
		if(next == -1){
			state.unfinish(); return true;
		}
		state.feed(next);
		content.append((char) next);
		return state.isClosed();
	}
	
	public boolean isValid(){
		return state.isFinished();
	}
	
	public CodePart get() throws CclCompileException{
		CodePart part = new CodePart(content.toString(), startLine, location);
		try{
			state.check();
		}catch(CclCompileException e){
			throw new CclCompileException("Invalid code part! Description:\n" + part.getCodeArea(), e);
		}
		return part;
	}

	public CodePartReadState getState() {
		return state;
	}

}
