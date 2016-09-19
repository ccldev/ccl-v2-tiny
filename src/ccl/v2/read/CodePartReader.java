package ccl.v2.read;

import java.io.File;
import java.io.FileNotFoundException;

import ccl.v1.read.Input;
import ccl.v1.read.InputFactory;
import ccl.v2.CclCompileException;

public class CodePartReader {
	
	private Input input;
	private int line = 0;
	private String srcInfo;

	public static CodePartReader make(File f) throws FileNotFoundException{
		return new CodePartReader(InputFactory.file(f), f.getAbsolutePath());
	}
	public static CodePartReader make(String s){
		return new CodePartReader(InputFactory.string(s), "String content\n" + s);
	}
	
	public CodePartReader(Input i, String srcInfo){
		this.input = i;
		this.srcInfo = srcInfo;
	}
	
	public CodePartReadResult next() throws CclCompileException{
		CodePartReadResult res = new CodePartReadResult(srcInfo, line);
		while(!res.feed(act(input.next())));
		return res;
	}

	private int act(int next) {
		if(next == '\n') line++;
		return next;
	}
	
}
