package com.mlosee.tm;

import com.mlosee.tm.exception.EdgeNotFoundException;
import com.mlosee.tm.exception.HeadCrashException;

public class TuringMachine {
	private Tape tape;
	private State start;
	
	public TuringMachine(State start){
		this.start = start;
	}
	
	public boolean processData(String input){
		tape = new Tape(input);
		
		return readNextInputFromTape(start);
	}
	
	/**
	 * Causes a state transition.
	 */
	private boolean readNextInputFromTape(State state){
		
		if(state.isHaltState()){			
			return true;
		}
		
		try {
			String input = tape.readCurrentInput();
			
			Edge edge = state.getEdge(input);
			String output = edge.getOutput();
			Direction direction = edge.getDirection();
			
			System.out.println("Tape: " + tape.toString() + " State: " + state.getName());
			
			tape.writeAndMoveHead(output, direction);
			
			return readNextInputFromTape(edge.getNextState());
			
		} catch (EdgeNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (HeadCrashException e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	public void printData(String input, String output, int direction){
		System.out.println("input: " + input + " output: " + output + " direction: " + direction);

		
	}
	
}
