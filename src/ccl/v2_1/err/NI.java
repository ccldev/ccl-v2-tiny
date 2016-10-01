package ccl.v2_1.err;

public class NI extends RuntimeException {

	public NI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NI(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public NI(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NI(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NI(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public NI(Object info){
		super(String.valueOf(info));
	}

}
