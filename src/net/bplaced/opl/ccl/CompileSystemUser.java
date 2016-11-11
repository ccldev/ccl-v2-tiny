package net.bplaced.opl.ccl;

import java.io.IOException;
import java.util.ArrayList;


import ccl.v2_1.bridge.OldCompileSystem;
import ccl.v2_1.bridge.OldWrapper;
import ccl.v2_1.compile.CclCompileResult;
import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public class CompileSystemUser<I, O> {
	
	private ArrayList<CompileSystem<I, O>> systems;
	
	public CompileSystemUser(){
		systems = new ArrayList<>();
	}
	
	public CclCompileResult<O> get(I input) throws DebugException, ImplementationException, IOException{
		for(int i = 0; i < systems.size(); i++){
			CompileSystem<I, O> sys = systems.get(i);
			if(sys.accept(input)){
				return new CclCompileResult<O>(sys.compileComplete(input), sys.getOutput(), sys.include());
			}
		}
		throw new DebugException("No matching compile System found!");
	}
	
	public void add(CompileSystem<I, O> sys){
		systems.add(sys);
	}

	@SuppressWarnings("unchecked")
	public void add(OldCompileSystem sys) {
		add((CompileSystem<I, O>) new OldWrapper(sys));
	}
	
}
