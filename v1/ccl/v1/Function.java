package ccl.v1;

public interface Function<P, R> {
	
	R call(P arg);
	
}
