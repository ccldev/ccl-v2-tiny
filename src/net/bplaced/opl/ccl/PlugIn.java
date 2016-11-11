package net.bplaced.opl.ccl;

public interface PlugIn<I, O> extends CompileSystem<I, O>{
	
	CompileSystemUser<I, O> getUser();
	
}
