package ccl.v2.routine.impl.value.todo;

import ccl.iface.debug.Logger;

public class TodoDescription {

	private String raw;
	private TodoType type;

	public TodoDescription(String todo) {
		this.raw = todo.trim();
		analyze();
	}

	private void analyze() {
		type = computeType();
	}
	
	private TodoType computeType(){
		char start = raw.charAt(0);
		switch(start){
		case '(': return TodoType.INVOKE;
		case '.': return TodoType.GET;
		default: throw new RuntimeException("Unknown todo type identifier " + start);
		}
	}

	public TodoResult compile(int layer, String compiledBase) {
		TodoElements elements = new TodoElements();
		for(int i = 0; i < raw.length(); i++){
			elements.feed(raw.charAt(i));
		}
		return elements.compile(layer);
	}

}
