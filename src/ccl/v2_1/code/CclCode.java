package ccl.v2_1.code;

import java.util.List;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;
import ccl.v2_1.err.NI;
import ccl.v2_1.layers.LayerSystem;

public class CclCode {
	
	private String raw;

	public CclCode(String code){
		this.raw = code;
	}
	
	public CclCodePart[] buildCodeParts() throws ImplementationException, DebugException{
		return buildCodeParts(0);
	}
	
	public CclCodePart[] buildCodeParts(int minLayer) throws ImplementationException, DebugException{
		LayerSystem sys = new LayerSystem('{', '}', ';');
		sys.setMinimumLayer(minLayer);
		if(!sys.isAllZero()) throw new ImplementationException("Layer System must be 'all-zero' after creation!", sys);
		if(sys.isFinished()) throw new ImplementationException("Layer System may not be finished after creation!", sys);
		
		sys.feed(raw);
		
		return buildCodeParts0(sys.getList());
	}

	private CclCodePart[] buildCodeParts0(List<String> list) throws ImplementationException, DebugException {
		CclCodePart[] arr = new CclCodePart[list.size()];
		for(int i = 0; i < list.size(); i++){
			arr[i] = new CclCodePart(list.get(i).trim());
		}
		return arr;
	}

	public String getRaw() {
		return raw;
	}
	
}
