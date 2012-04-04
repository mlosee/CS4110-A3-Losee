package com.mlosee.tm;


public class HaltState extends State {

	public HaltState(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public Edge getEdge(String input) {
		if(!edgeMap.containsKey(input)){
			edgeMap.put(input, new Edge(input, input, Move.RIGHT, this));
		}
		return edgeMap.get(input);
	}
	
	public boolean isHaltState(){
		return true;
	}

}
