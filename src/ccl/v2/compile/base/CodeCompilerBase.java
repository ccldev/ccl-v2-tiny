package ccl.v2.compile.base;

import ccl.v2.CclCompileException;
import ccl.v2.compile.categorize.CodeBlock;
import ccl.v2.compile.categorize.CodeSnippet;
import ccl.v2.compile.debug.CodePartCompileResultCollection;
import ccl.v2.compile.debug.CodePartCompiler;
import ccl.v2.read.CodePartReader;

import static ccl.v2.compile.base.BlockHeaderCompiler.*;

public class CodeCompilerBase {

	public static String compileBlock(CodeBlock block) throws CclCompileException {
		block.getContent();
		CodePartReader reader = block.getContent().makeReader();
		CodePartCompileResultCollection collection = CodePartCompiler.compileAll(reader);
		return makeBlockPrefix(block.getHeader()) + "\n" + collection.getRaw() + makeBlockSuffix(block.getHeader());
	}

	public static String compileSnippet(CodeSnippet snippet) throws CclCompileException {
		return SnippetCompileRoutinePool.use(snippet);
	}

}
