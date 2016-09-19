package ccl.v2.routine.impl.value.base;

import ccl.v2.routine.impl.value.CompiledValue;
import ccl.v2.routine.impl.value.ValueRepresentation;
import ccl.v2.routine.impl.value.todo.TodoResult;

public abstract class AbstractCompiledValue extends CompiledValue{
	
	protected ValueRepresentation value;
	
	public AbstractCompiledValue(ValueRepresentation val){
		this.value = val;
		String base = compileBase(val.getBaseValue());
		TodoResult todo = compileTodo(base, val.getTodo());
		setCompiled(todo.getFirst() + base + todo.getLast());
		setBefore(todo.getBefore() + beforeBase(val.getBaseValue()));
	}
	
	protected abstract String compileBase(String base);
	protected abstract TodoResult compileTodo(String compiledBase, String todo);
	protected abstract String beforeBase(String base);
	
}
