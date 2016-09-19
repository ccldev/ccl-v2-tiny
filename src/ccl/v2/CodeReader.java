package ccl.v2;

import ccl.v1.read.Input;

public class CodeReader {
	
	private Input input;
	
	private int layer;
	private boolean unescape;
	private boolean inString;

	public CodeReader(Input in){
		this.input = in;
	}
	
	public CodePart next(){
		StringBuilder builder = new StringBuilder();
		
		int val;
		loop:
		while((val = input.next()) >= 0){
			builder.append((char) val);
			if(unescape){unescape = false; continue;}
			if(inString){
				if(val == '"') inString = false;
			}
			switch(val){
			case '\\': unescape = true; break;
			case '{': layer++; break;
			case '}': layer--; if(layer == 0) break loop; break;
			case '"': inString = true; break;
			case ';': if(!inString && layer==0) break loop;
			}
		}
		
		if(layer != 0){
			System.err.println(builder);
			throw new RuntimeException("Invalid Code part! layer=" + layer);
		}
		if(unescape){
			System.err.println(builder);
			throw new RuntimeException("Invalid Code part! unescape is true");
		}
		if(inString){
			System.err.println(builder);
			throw new RuntimeException("Invalid Code part! inString is true");
		}
		return new CodePart(builder.toString());
	}
	
}
