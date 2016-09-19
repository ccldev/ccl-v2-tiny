package ccl.v2.routine.impl.value.todo;

public class TodoCompiler {
	
	public static TodoResult compileTodo(int layer, String compiledBase, String todo){
		todo = todo.trim();
		if(todo.length() == 0) return new TodoResult();
		TodoDescription description = new TodoDescription(todo);
		return description.compile(layer, compiledBase);
	}
	
}
