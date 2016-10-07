package ccl.v2_1.err;

public class ImplementationException extends Exception{
	
	private static final long serialVersionUID = 1864781876470279007L;

	public ImplementationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImplementationException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public ImplementationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ImplementationException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ImplementationException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ImplementationException(String string, Object... data) {
		super(string);
		for(int i = 0; i < data.length; i++){
			System.err.println(data[i]);
		}
	}

}
