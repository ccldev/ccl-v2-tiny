package ccl.v2.routine.impl.value.todo;

import java.util.ArrayList;

public class TodoElements extends ArrayList<TodoElement>{
	
	private static final long serialVersionUID = -6378135168184486211L;
	
	private int depth = 0;
	private TodoElement current;
	private boolean unescape;
	
	public TodoElements(){
		current = TodoElement.make();
	}
	
	public void feed(char next){
		if(unescape){
			current.feed(next);
			unescape = false;
		}else if(next == '\\'){
			unescape = true;
		}else if(next == '('){
			depth++;
			current = current.child();
		}else if(next == ')'){
			depth--;
			current = current.parent();
			closeElement();
		}else{
			current.feed(next);
		}
	}

	private void closeElement() {
		if(depth == 0){
			if(!current.isEmpty()){
				add(current);
			}
		}
		current = TodoElement.make();
	}

	public TodoResult compile(int layer) {
		if(current != null){
			closeElement();
			current = null;
		}
		throw new RuntimeException("NI " + this);
	}
	
}
