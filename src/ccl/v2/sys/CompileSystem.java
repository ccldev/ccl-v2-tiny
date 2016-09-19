package ccl.v2.sys;

public interface CompileSystem {
	
	boolean accept(String snippet);
	String compile(String snippet);
	
}
