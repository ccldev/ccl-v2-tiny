package ccl.v2.cpl;

import java.util.HashMap;

public abstract class BlockSurround {

	private static HashMap<String, BlockSurround> map = new HashMap<>();
	
	public static BlockSurround get(String keyword) {
		return map.get(keyword);
	}
	
	public static void add(String keyword, BlockSurround surround){
		map.put(keyword, surround);
	}

	public abstract String getBefore(String keyword, String condition);
	public abstract String getAfter(String keyword, String condition);

}
