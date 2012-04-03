package com.mlosee.tm;

import java.util.HashMap;
import java.util.Map;

public class TuringMachineFactory {
	
	
	public static TuringMachine createTM(String[] args){
		Map<String, State> stateMap = new HashMap<String, State>();
		
		for(String s : args){
			String[] arg = s.split(",");
			String stateNumber = arg[0];
			String input = arg[1].split("=>")[0];
			String output = arg[1].split("=>")[1];
			Direction direction = Direction.getByValue(arg[2]);
			State nextState = getState(stateMap, arg[3]);
			
			State state = getState(stateMap, stateNumber);
			state.putEdge(input, output, direction, nextState);
		}
		
		State start = stateMap.get("0");
		return new TuringMachine(start);
	}
	
	private static State getState(Map<String, State> stateMap, String key){
		if(!stateMap.containsKey(key)){
			stateMap.put(key, new State(key));
		}
		
		return stateMap.get(key);
	}
	
	
}

