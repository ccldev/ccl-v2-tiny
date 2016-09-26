package ccl.v2;

import ccl.v1.read.Input;
import ccl.v2.layer.LayerExit;
import ccl.v2.layer.LayerSystem;

public class CodeReader {
	
	private Input input;

	public CodeReader(Input in){
		this.input = in;
	}
	
	public CodePart next(){
		LayerSystem sys = new LayerSystem('{', '}',false, LayerExit.EXIT_ON_ZERO, ';');
		
		sys.feed(input);
		sys.check();
		
		return new CodePart(sys.getList().get(0));
	}
	
}
