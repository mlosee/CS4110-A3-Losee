package com.mlosee.tm;

import java.util.ArrayList;
import java.util.List;

import com.mlosee.tm.exception.HeadCrashException;

public class Tape {
	private List<String> tape;
	private int head;
	
	public Tape(String input){
		head = 1;
		tape = new ArrayList<String>();
		tape.add("#");
		for(int i = 0; i < input.length(); i++){
			tape.add(input.substring(i, i+1));
		}
	}
	
	public String readCurrentInput() throws HeadCrashException{
		if(head >= tape.size()){
			throw new HeadCrashException();
		}
		
		return tape.get(head);
		
	}
	
	public boolean atEnd(){
		return head == tape.size();
	}
	
	public void writeAndMoveHead(String output, Direction direction) throws HeadCrashException {
		int newHeadPosition = (head + direction.value);
		
		if(newHeadPosition + head < 0){
			throw new HeadCrashException();
		}
		
		
			tape.set(head, output);		
			head = newHeadPosition;

				
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder();
		for(String s : tape){
			b.append(s);
		}
		return b.toString();
	}
	

}


//for(int i = 0; i < input.length(); i++){