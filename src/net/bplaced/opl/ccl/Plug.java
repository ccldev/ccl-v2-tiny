package net.bplaced.opl.ccl;

public class Plug {
	
	public static <I, O> void in(PlugIn<I, O> plugin){
		plugin.getList().add(plugin);
	}
	
}
