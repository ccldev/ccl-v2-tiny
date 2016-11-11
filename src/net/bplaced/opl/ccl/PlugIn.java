package net.bplaced.opl.ccl;

import java.util.List;


public interface PlugIn<I, O> extends CompileSystem<I, O>{
	
	List<CompileSystem<I, O>> getList();
	
}
