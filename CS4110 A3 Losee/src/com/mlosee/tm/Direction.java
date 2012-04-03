package com.mlosee.tm;

import java.util.HashMap;
import java.util.Map;

/**
 * Cool stringToEnum thing inspired by stackoverflow post
 * http://stackoverflow.com/questions/240122/how-to-convert-string-result-of-enum-with-overridden-tostring-back-to-enum
 *
 */

public enum Direction {
	L(-1), R(1);
	public final int value;
	Direction(int value){
		this.value = value;
	}
	
	private static final Map<String, Direction> stringToEnum =  new HashMap<String, Direction>();
	
	static {
		for(Direction d : values()){
			stringToEnum.put(d.toString(), d);
		}
	}
	
	public static Direction getByValue(String value){
		return stringToEnum.get(value);
	}
}
