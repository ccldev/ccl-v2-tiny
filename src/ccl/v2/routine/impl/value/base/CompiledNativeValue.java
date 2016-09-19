package ccl.v2.routine.impl.value.base;

import ccl.iface.debug.Logger;
import ccl.v2.routine.impl.value.ValueRepresentation;
import ccl.v2.routine.impl.value.todo.TodoCompiler;
import ccl.v2.routine.impl.value.todo.TodoResult;

public class CompiledNativeValue extends AbstractCompiledValue {

	public CompiledNativeValue(ValueRepresentation val) {
		super(val);
	}

	@Override
	protected String compileBase(String base) {
		return "N" + base;
	}

	@Override
	protected TodoResult compileTodo(String compiledBase, String todo) {
		return TodoCompiler.compileTodo(value.getLayer(), compiledBase, todo);
	}

	@Override
	protected String beforeBase(String base) {
		Logger.out.log(this, "TODO: implement beforeBase(String)");
		return "";
	}

}
