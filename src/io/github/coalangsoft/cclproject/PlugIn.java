package io.github.coalangsoft.cclproject;

public interface PlugIn<I, O> extends CompileSystem<I, O>{
	
	CompileSystemUser<I, O> getUser();
	
}
