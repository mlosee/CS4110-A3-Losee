package com.mlosee.tm;

public class Edge {
	
	private String input, output;	
	private State nextState;
	private Move direction;
	
	public Edge(String input, String output,  Move direction, State nextState) {		
		this.input = input;
		this.output = output;
		this.nextState = nextState;
		this.direction = direction;
	}
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public State getNextState() {
		return nextState;
	}
	public void setNextState(State nextState) {
		this.nextState = nextState;
	}
	public Move getDirection() {
		return direction;
	}
	public void setDirection(Move direction) {
		this.direction = direction;
	}
}
