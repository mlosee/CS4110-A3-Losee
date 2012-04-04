package com.mlosee.tm;

import java.util.HashMap;
import java.util.Map;

/**
 * Cool stringToEnum thing inspired by stackoverflow post
 * http://stackoverflow.com/questions/240122/how-to-convert-string-result-of-enum-with-overridden-tostring-back-to-enum
 *
 */

public enum Move {
	LEFT(-1), RIGHT(1);
	public final int value;
	Move(int value){
		this.value = value;
	}
	
	private static final Map<String, Move> stringToEnum =  new HashMap<String, Move>();
	
	static {
		for(Move d : values()){
			stringToEnum.put(d.toString().substring(0, 1), d);
		}
	}
	
	public static Move getByValue(String value){
		return stringToEnum.get(value);
	}
}
