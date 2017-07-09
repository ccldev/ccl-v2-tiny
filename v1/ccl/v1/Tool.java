package ccl.v1;

import java.util.ArrayList;

public class Tool {

	public static String[] split(String code, char split) {
		boolean unescape = false;
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < code.length(); i++){
			char c = code.charAt(i);
			if(unescape){
				if(c == 'n'){
					builder.append("\\n");
				}else if(c == 't'){
					builder.append("\t");
				}else{
					builder.append(c);
				}
				unescape = false;
			}else if(c == '\\'){
				unescape = true;
			}else if(c == split){
				list.add(builder.toString());
				builder = new StringBuilder();
			}else{
				builder.append(c);
			}
		}
		list.add(builder.toString());
		return list.toArray(new String[0]);
	}

}
