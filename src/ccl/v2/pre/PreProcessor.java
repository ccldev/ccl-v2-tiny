package ccl.v2.pre;

import java.util.Scanner;

import ccl.v1.Function;
import ccl.v1.read.Input;
import ccl.v1.read.InputFactory;
import ccl.v1.read.LineReader;

public class PreProcessor {
	
	private Input input;
	private StringBuilder content;
	private boolean acted;
	
	public PreProcessor(Input input) {
		super();
		this.input = input;
	}

	public void read(){
		content = new StringBuilder();
		int val;
		while(!((val = input.next()) < 0)){
			content.append((char) val);
		}
	}
	
	public String act(){
		LineReader<String> reader = new LineReader<String>(new Scanner(content.toString()), new Function<String, String>(){

			@Override
			public String call(String arg) {
				return arg;
			}
			
		});
		
		StringBuilder res = new StringBuilder();
		
		while(reader.hasNext()){
			res.append(process(reader.read()) + "\n");
		}
		
		if(acted){
			acted = false;
			input = InputFactory.string(res.toString());
			read();
			return act();
		}
		
		return res.toString();
	}

	private String process(String line) {
		line = line.trim();
		if(line.startsWith("#")){
			acted = true;
			return PreSystems.use(line.substring(1));
		}
		return line;
	}

}
