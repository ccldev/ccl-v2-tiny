package ccl.v2.pre;

import java.util.ArrayList;

import ccl.v2.sys.CompileSystem;

public class PreSystems {
	
	private static ArrayList<CompileSystem> systems = new ArrayList<CompileSystem>();
	
	public static void add(CompileSystem sys){
		systems.add(sys);
	}
	
	public static String use(String line){
		for(int i = 0; i < systems.size(); i++){
			CompileSystem sys = systems.get(i);
			if(sys.accept(line)) return sys.compile(line);
		}
		throw new RuntimeException("No system found for " + line);
	}
	
}
