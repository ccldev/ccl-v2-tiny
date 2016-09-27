package ccl.v2.layer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ccl.v1.read.Input;

public class LayerSystem {

	private boolean inString = false;
	private boolean unescape = false;
	private int layer = 0;
	private List<String> list = new ArrayList<String>();
	
	private char[] opener;
	private char[] closer;
	private LayerExit exit;
	private char[] zeroBreakers;
	private boolean seperate;
	
	public List<String> getList() {
		return list;
	}

	public char[] getOpener() {
		return opener;
	}

	public char[] getCloser() {
		return closer;
	}

	public LayerExit getExit() {
		return exit;
	}

	public LayerSystem(char opener, char closer, boolean seperate, LayerExit exit, char... zeroBreakers){
		this.opener = new char[]{opener};
		this.closer = new char[]{closer};
		this.exit = exit;
		this.zeroBreakers = zeroBreakers;
		this.seperate = seperate;
	}
	public LayerSystem(char[] opener, char[] closer, boolean seperate, LayerExit exit, char... zeroBreakers){
		this.opener = opener;
		this.closer = closer;
		this.exit = exit;
		this.zeroBreakers = zeroBreakers;
		this.seperate = seperate;
	}
	
	public void reset(){
		inString = false;
		unescape = false;
		layer = 0;
		list = new ArrayList<String>();
	}
	
	public int feed(Input in){
		StringBuilder temp = new StringBuilder();
		int ctr = -1;
		loop:
		for(int i = in.next(); i >= 0; i = in.next()){
			ctr++;
			char c = (char) i;
			temp.append(c);
			if(unescape){
				unescape = false;
				continue;
			}
			if(inString){
				if(c == '"') inString = false;
				continue;
			}
			if(c == '"'){
				inString = true;
			}else if(Arrays.asList(opener).contains(c)){
				layer++;
			}else if(Arrays.asList(closer).contains(c)){
				layer--;
				if(layer <= -1) throw new RuntimeException("Negative layer " + layer);
				if(layer == 0){
					list.add(temp.toString());
					temp = new StringBuilder();
					if(exit == LayerExit.EXIT_ON_ZERO) return ctr;
				}
			}else if(c == ','){
				System.out.println(layer);
				if(layer == 0 && seperate){
					String str = temp.toString();
					list.add(str.substring(0, str.length() - 1).trim());
					temp = new StringBuilder();
				}
			}else{
				for(int k = 0; k < zeroBreakers.length; k++){
					if(c == zeroBreakers[k]){
						if(layer == 0) break loop;
					}
				}
			}
		}
		if(list.size() == 0){
			list.add(temp.toString());
		}else{
			String s = temp.toString().trim();
			if(!s.isEmpty()){
				list.add(s);
			}
		}
		return -1;
	}
	
	public void check(){
		if(layer != 0){
			System.err.println(list);
			throw new RuntimeException("Invalid Code part! layer=" + layer);
		}
		if(unescape){
			System.err.println(list);
			throw new RuntimeException("Invalid Code part! unescape is true");
		}
		if(inString){
			System.err.println(list);
			throw new RuntimeException("Invalid Code part! inString is true");
		}
	}

}
