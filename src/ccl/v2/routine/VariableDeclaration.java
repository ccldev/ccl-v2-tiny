package ccl.v2.routine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccl.v2.compile.base.CompileRoutine;
import ccl.v2.compile.categorize.CodeSnippet;
import ccl.v2.routine.impl.VariableDeclarationInfo;
import ccl.v2.routine.impl.value.CompiledValue;

public class VariableDeclaration implements CompileRoutine{

	@Override
	public String call(CodeSnippet snippet) {
		Matcher m = Pattern.compile(getPattern()).matcher(snippet.getRaw());
		m.find();
		VariableDeclarationInfo var = VariableDeclarationInfo.make(m);
		CompiledValue val = var.getValue().compile();
		return val.getBefore() + "\nV1::" + var.getName() + ":" + val.getCompiled();
	}

	@Override
	public String getPattern() {
		return "\\s*var\\s+([a-zA-Z0-9]*)\\s*=\\s*(.*)\\s*;";
	}

}
