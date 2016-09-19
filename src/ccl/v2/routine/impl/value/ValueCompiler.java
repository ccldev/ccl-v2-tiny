package ccl.v2.routine.impl.value;

import ccl.v2.routine.impl.value.base.CompiledNativeValue;

public class ValueCompiler {
	
	public static CompiledValue compile(ValueRepresentation val){
		switch(val.getType()){
		case NATIVE: return new CompiledNativeValue(val);
		default: throw new RuntimeException("Value compile not implemented for type " + val.getType());
		}
	}
	
}
