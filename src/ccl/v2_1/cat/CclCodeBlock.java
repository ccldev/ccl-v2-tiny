package ccl.v2_1.cat;

import ccl.v2_1.code.CclCodePart;
import ccl.v2_1.err.NI;

public class CclCodeBlock {

	public CclCodeBlock(CclCodePart codePart) {
		System.out.println(codePart);
	}

	public String compile() {
		throw new NI("NI");
	}

}
