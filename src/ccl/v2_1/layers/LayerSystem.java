package ccl.v2_1.layers;

import java.util.ArrayList;
import java.util.List;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;
import ccl.v2_1.operators.Operators;

public class LayerSystem {
	
	public LayerSystem(char[] openers, char[] closers, char... breakers) throws ImplementationException {
		state = new LayerState(openers, closers, breakers);
		initInstance();
	}
	
	public LayerSystem(char opener, char closer, char... breakers) throws ImplementationException{
		state = new LayerState(new char[]{opener}, new char[]{closer}, breakers);
		initInstance();
	}
	
	private void initInstance() {
		current = new StringBuilder();
		list = new ArrayList<String>();
	}

	private LayerState state;
	private boolean finished;
	private StringBuilder current;
	private int lastLayer;
	private int minimumLayer;

	private List<String> list;
	
	public void setMinimumLayer(int minimumLayer) {
		this.minimumLayer = minimumLayer;
	}
	
	public boolean isAllZero() {
		return isBiggest(0);
	}

	public boolean isFinished() {
		return finished;
	}

	public void feed(String code) {
		for(int i = 0; i < code.length(); i++){
			feed0(code.charAt(i));
		}
	}
	
	public List<String> getList(){
		if(current.toString().length() > 0){
			list.add(current.toString());
			current = new StringBuilder();
		}
		return list;
	}

	private void feed0(char c) {
		if(finished){
			finished = false;
		}
		
		boolean b1 = state.get(Operators.GREATER_THEN) >= minimumLayer;
		state.feed(c);
		boolean b2 = state.get(Operators.GREATER_THEN) >= minimumLayer;
		
		if(b1 && b2){
			current.append(c);
		}
		
		finished = isBiggest(minimumLayer) && lastLayer > minimumLayer || isBiggest(minimumLayer) && state.isBreaker(c);
		lastLayer = state.get(Operators.GREATER_THEN);
		if(finished){
			list.add(current.toString());
			current = new StringBuilder();
		}
	}

	private boolean isBiggest(int layer) {
		return state.get(Operators.GREATER_THEN) == layer;
	}
	
	public void check() throws DebugException{
		if(!isAllZero()) throw new DebugException("All layers should be zero at this point!\n: " + state.getDebugInfo());
	}

	@Override
	public String toString() {
		return "LayerSystem [state=" + state + ", finished=" + finished
				+ ", current=" + current + ", lastLayer=" + lastLayer
				+ ", minimumLayer=" + minimumLayer + ", list=" + list + "]";
	}
	
}
