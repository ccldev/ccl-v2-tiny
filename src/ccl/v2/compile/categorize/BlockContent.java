package ccl.v2.compile.categorize;

import ccl.v2.read.CodePartReader;

public class BlockContent {

	private String raw;

	public BlockContent(String raw) {
		this.raw = raw.trim();
	}

	public String getRaw() {
		return raw;
	}
	
	public CodePartReader makeReader(){
		return CodePartReader.make(getRaw());
	}

}
