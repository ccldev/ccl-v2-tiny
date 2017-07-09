package io.github.coalangsoft.cclproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class Plug {
	
	public static <I, O> void in(PlugIn<I, O> plugin){
		plugin.getUser().add(plugin);
	}
	
	public static void loadAll(File dir) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException{
		File[] list = dir.listFiles();
		if(list == null){
			System.err.println("File " + dir + " does not contain children!");
			return;
		}else{
			for(int i = 0; i < list.length; i++){
				load(list[i]);
			}
		}
	}

	private static void load(File file) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		URLClassLoader loader = new URLClassLoader(new URL[]{
			file.toURI().toURL()
		});
		Scanner s = new Scanner(loader.getResourceAsStream("ccl-plugin"));
		while(s.hasNextLine()){
			plug(loader.loadClass(s.nextLine()));
		}
		loader.close();
		s.close();
	}

	@SuppressWarnings("unchecked")
	private static void plug(Class<?> c) throws InstantiationException, IllegalAccessException {
		in((PlugIn<? extends Object, ? extends Object>) c.newInstance());
	}
	
}
