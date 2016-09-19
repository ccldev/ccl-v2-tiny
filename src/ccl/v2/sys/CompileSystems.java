package ccl.v2.sys;

import java.util.ArrayList;

public class CompileSystems {
	
	private static ArrayList<CompileSystem> list = new ArrayList<CompileSystem>();
	
	public static String use(String snippet){
		for(int i = 0; i < list.size(); i++){
			CompileSystem sys = list.get(i);
			if(sys.accept(snippet)){
				return sys.compile(snippet);
			}
		}
		System.err.println(snippet);
		throw new RuntimeException("No matching system found!");
	}
	
	public static void add(CompileSystem sys){
		list.add(sys);
	}
	
}
