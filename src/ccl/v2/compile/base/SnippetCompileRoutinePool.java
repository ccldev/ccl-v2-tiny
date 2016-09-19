package ccl.v2.compile.base;

import java.util.ArrayList;
import java.util.List;

import ccl.v2.CclCompileException;
import ccl.v2.compile.categorize.CodeSnippet;

public class SnippetCompileRoutinePool {
	
	private static List<CompileRoutine> routines = new ArrayList<CompileRoutine>();
	
	public static void add(CompileRoutine r){
		routines.add(r);
	}

	public static String use(CodeSnippet snippet) throws CclCompileException {
		for(int i = 0; i < routines.size(); i++){
			CompileRoutine r = routines.get(i);
			if(snippet.matches(r.getPattern())){
				return r.call(snippet);
			}
		}
		throw new CclCompileException("Unable to compile code snippet! " + snippet);
	}
	
}
