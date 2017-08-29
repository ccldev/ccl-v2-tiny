package ccl.v2_1.layers;

import ccl.v2_1.err.DebugException;
import io.github.coalangsoft.lib.data.Func;

import java.util.Arrays;

import ccl.v2_1.err.ImplementationException;
import ccl.v2_1.operators.Operators;

public class LayerState {
	
	private boolean inString;
	private boolean unescaped;
	private int[] layers;
	private char[] openers;
	private char[] closers;
	private char[] breakers;
	
	public LayerState(char[] openers, char[] closers, char... breakers) throws ImplementationException {
		if(openers.length != closers.length){
			throw new ImplementationException("Every opener needs a closer!", openers, closers);
		}
		this.openers = openers;
		this.closers = closers;
		this.layers = new int[openers.length];
		this.breakers = breakers;
	}

	public int get(Func<Integer[], Boolean> op) {
		Integer ret = null;
		for(int i = 0; i < layers.length; i++){
			if(ret == null){
				ret = layers[i]; continue;
			}
			ret = op.call(new Integer[]{layers[i], ret}) ? layers[i] : ret;
		}
		return ret == null ? 0 : ret;
	}

	public void feed(char c) {
		if(unescaped){
			unescaped = false;
			return;
		}
		if(c == '\\'){
			unescaped = true;
			return;
		}
		if(!isInUse('"')){
			if(inString){
				if(c == '"'){
					inString = false;
				}
				return;
			}
			if(c == '"'){
				inString = true;
				return;
			}
		}
		for(int i = 0; i < layers.length; i++){
			if(c == openers[i] && c == closers[i]){
				layers[i] = layers[i] == 0 ? 1 : 0;
				continue;
			}
			if(c == openers[i]){
				layers[i]++;
			}
			if(c == closers[i]){
				layers[i]--;
				if(layers[i] < 0){
					throw new RuntimeException("Negative layer depth - Layer closed too often!\n: " + getDebugInfo());
				}
			}
		}
	}

	private boolean isInUse(char c) {
		for(int i = 0; i < openers.length; i++){
			if(openers[i] == c) return true;
		}
		for(int i = 0; i < closers.length; i++){
			if(closers[i] == c) return true;
		}
		return false;
	}

	public boolean isBreaker(char c) {
		for(int i = 0; i < breakers.length; i++){
			if(breakers[i] == c) return true;
		}
		return false;
	}
	
	public boolean isBiggest(int layer) {
		return get(Operators.GREATER_THAN) == layer;
	}

	@Override
	public String toString() {
		return "LayerState [inString=" + inString + ", layers="
				+ Arrays.toString(layers) + ", openers="
				+ Arrays.toString(openers) + ", closers="
				+ Arrays.toString(closers) + ", breakers="
				+ Arrays.toString(breakers) + "]";
	}

	public String getDebugInfo() {
		StringBuilder builder = new StringBuilder();
		if(inString){
			builder.append("You forgot to close a String!\n");
		}
		for(int i = 0; i < layers.length; i++){
			if(layers[i] > 0){
				builder.append("You opened a layer, but it seems like you forgot to close it!\n");
			}else if(layers[i] < 0){
				builder.append("You closed a layer more often then opening it!\n");
			}
			if(layers[i] != 0){
				builder.append("> Layer opener: " + openers[i] + "\n");
				builder.append("> Layer closer: " + closers[i] + "\n");
				builder.append("> Layer depth: " + layers[i] + "\n");
			}
		}
		return builder.toString();
	}
	
	public boolean inProgress(){
		return inString || unescaped;
	}
	
}
