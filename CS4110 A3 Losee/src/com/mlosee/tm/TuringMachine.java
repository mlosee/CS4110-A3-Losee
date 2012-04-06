package com.mlosee.tm;


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
		System.out.println("Tape: " + tape.toString() + " State: " + state.getName());
		
		try {
			String input = tape.readCurrentInput();
			
			Edge edge = state.getEdge(input);
			String output = edge.getOutput();
			Move direction = edge.getDirection();
			
//			Thread.sleep(90);
			
			tape.writeAndMoveHead(output, direction);
			
			return state.isHaltState() ? true : readNextInputFromTape(edge.getNextState());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
		return false;
	}
}
