package ccl.v2.routine.impl;

import java.util.regex.Matcher;

import ccl.v2.routine.impl.value.ValueRepresentation;

public class VariableDeclarationInfo {
	
	public static VariableDeclarationInfo make(Matcher m){
		return new VariableDeclarationInfo(m.group(1), m.group(2));
	}
	
	private String name;
	private String value;
	
	public VariableDeclarationInfo(String name, String value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public ValueRepresentation getValue() {
		return ValueRepresentation.make(value);
	}

	@Override
	public String toString() {
		return "VariableInfo [name=" + name + ", value=" + value + "]";
	}
	
}
