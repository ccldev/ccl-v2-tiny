package io.github.coalangsoft.cclproject;

import java.io.IOException;
import java.util.ArrayList;

import ccl.v2_1.compile.CclCompileResult;
import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;

public class CompileSystemUser<I, O> {
	
	private ArrayList<CompileSystem<I, O>> systems;
	private CompileSystem<I, O> def;
	
	public CompileSystemUser(){
		systems = new ArrayList<>();
	}
	
	public CompileSystemUser<I, O> setDefault(CompileSystem<I, O> def){
		this.def = def;
		return this;
	}
	
	public CclCompileResult<O> get(I input) throws DebugException, ImplementationException, IOException{
		for(int i = 0; i < systems.size(); i++){
			CompileSystem<I, O> sys = systems.get(i);
			if(sys.accept(input)){
				return new CclCompileResult<O>(sys.compileComplete(input), sys.getOutput(), sys.include());
			}
		}
		if(def != null){
			return new CclCompileResult<O>(def.compileComplete(input), def.getOutput(), def.include());
		}
		throw new DebugException("No matching compile System found!");
	}
	
	public void add(CompileSystem<I, O> sys){
		systems.add(sys);
	}
	
}
