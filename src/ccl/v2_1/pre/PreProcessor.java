package ccl.v2_1.pre;

import java.io.IOException;

import ccl.csy.Constants;
import ccl.v2_1.layers.LayerState;
import ccl.v2_1.layers.LayerSystem;
import io.github.coalangsoft.cclproject.CompileSystems;

import ccl.v2_1.err.DebugException;
import ccl.v2_1.err.ImplementationException;
import io.github.coalangsoft.lib.log.TimeLogger;

import javax.swing.*;

public class PreProcessor {

	private StringBuilder builder = new StringBuilder();

	public void process(String nextLine) throws DebugException, ImplementationException, IOException {
		nextLine = nextLine.trim();
		if(nextLine.startsWith("//")){
			
		}else if(nextLine.startsWith("#")){
			builder.append(CompileSystems.PRE.get(nextLine).getResult());
		}else{
			builder.append(nextLine);
			builder.append("\n");
		}
	}

	public String get() {
		return builder.toString();
	}

	public String insertSemicolons(String result) throws ImplementationException {
		StringBuilder sb = new StringBuilder();

		boolean blockOpen = false;

		LayerState state = new LayerState(new char[]{'{'}, new char[]{'}'});
		for(int i = 0; i < result.length(); i++){
			state.feed(result.charAt(i));
			sb.append(result.charAt(i));

			if(state.isBiggest(0)){
				//Block finished?
				if(blockOpen){
					blockOpen = false;
					finishBlock(result, i, sb);
				}
			}else{
				//In block
				blockOpen = true;
			}
		}

		return sb.toString();
	}

	private void finishBlock(String result, int i, StringBuilder sb) {
		for(int k = i + 1; k < result.length(); k++){
			String charString = "" + result.charAt(k);
			if(charString.trim().isEmpty()){
				//Keep going
			}else if(charString.matches("[a-zA-Z0-0_@" + Constants.OPERATOR_CHARS + "]")){
				//Following is a name
				sb.append(';');
				return;
			}else{
				//Following is not a name - no semicolon
				return;
			}
		}
	}

}
