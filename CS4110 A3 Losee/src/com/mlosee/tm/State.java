package com.mlosee.tm;

import java.util.HashMap;
import java.util.Map;

import com.mlosee.tm.exception.EdgeNotFoundException;

/**
 *  Contains the data for each edge and works kind of like a linked list. 
 * 
 *
 */
public class State {
	private String name;
	protected Map<String, Edge> edgeMap = new HashMap<String, Edge>();
	
	public State(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void putEdge(String input, String output, Move direction, State nextState){
		edgeMap.put(input, new Edge(input, output, direction, nextState));
	}
		
	public Edge getEdge(String input) throws EdgeNotFoundException{
		if(!edgeMap.containsKey(input)){
			throw new EdgeNotFoundException();
		}
		return edgeMap.get(input);
	}

	public boolean isHaltState(){
		return false;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		for(String key : edgeMap.keySet()){
			Edge edge = edgeMap.get(key);
			sb.append("input: ");
			sb.append(key);
			sb.append(" output: ");
			sb.append(edge.getOutput());
			sb.append(" direction: ");
			sb.append(edge.getDirection().value);
			sb.append("\n");
		}
		
		return sb.toString();
	}
}