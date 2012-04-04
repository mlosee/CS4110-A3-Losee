package com.mlosee.tm.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mlosee.tm.Direction;
import com.mlosee.tm.State;
import com.mlosee.tm.Tape;
import com.mlosee.tm.TuringMachine;
import com.mlosee.tm.TuringMachineFactory;
import com.mlosee.tm.exception.EdgeNotFoundException;
import com.mlosee.tm.exception.HeadCrashException;

public class TMTests {
		
	@Test(expected=HeadCrashException.class)
	public void testTape() throws HeadCrashException {
		Tape crashTape = new Tape("aaba");
		crashTape.writeAndMoveHead("a", Direction.L);
		crashTape.writeAndMoveHead("a", Direction.L);
		
		Tape tape = new Tape("aaba");		
		assertEquals("a", tape.readCurrentInput());
		
		tape.writeAndMoveHead("b", Direction.R);
		assertEquals("#baba", tape.toString());		
		assertEquals("a", tape.readCurrentInput());
		tape.writeAndMoveHead("b", Direction.R);
		assertEquals("#bbba", tape.toString());	
		
		tape.writeAndMoveHead("a", Direction.R);
		tape.writeAndMoveHead("b", Direction.R);
		tape.writeAndMoveHead("a", Direction.R);
		tape.writeAndMoveHead("a", Direction.L);
		
		tape.writeAndMoveHead("a", Direction.R);
		tape.writeAndMoveHead("a", Direction.R);
		tape.writeAndMoveHead("a", Direction.R);
		tape.writeAndMoveHead("b", Direction.R);
		tape.writeAndMoveHead("b", Direction.R);
		assertEquals("#bbababb", tape.toString());	
	}

	@Test
	public void testGetByValue() {
		assertEquals(Direction.getByValue("L").value, -1);
		assertEquals(Direction.getByValue("R").value, 1);
	}
	
	@Test(expected=EdgeNotFoundException.class)
	public void testEdge() throws EdgeNotFoundException {
		State state0 = new State("0");
		State state1 = new State("1");
		
		
		state0.putEdge("a", "b", Direction.R, state1);
		state0.putEdge("a", "b", Direction.R, state1);
		
		assertEquals("exception!!!", state0.getEdge("zzz").getInput());
		assertEquals("a", state0.getEdge("a").getInput());
		assertEquals("b", state0.getEdge("a").getOutput());
		assertEquals(state1, state0.getEdge("a").getNextState());
		assertEquals(state1.isHaltState(), true);	
	}
	

//	@Test
//	public void testFactory(){
//		String[] args =
//		      {"0,a=>a,R,1",
//			       "0,b=>b,R,1",
//			       "1,b=>b,R,2",
//			       "2,a=>a,R,2",
//			       "2,b=>b,R,2",
//			       "2,#=>#,R,3"};
//
//		TuringMachine tm = TuringMachineFactory.createTM(args);
//		assertEquals(false, tm.processData("aa"));
//		assertEquals(false, tm.processData("baa"));
//		assertEquals(false, tm.processData("bab"));
//		assertEquals(true, tm.processData("bbbb"));
//		assertEquals(true, tm.processData("abaaaaaaaaaaaaaaaaa"));
//	}
//	
//	@Test
//	public void testFactory2(){
//		String[] args  =
//		      {"0,a=>A,R,1",
//		       "1,a=>a,R,1",
//		       "1,B=>B,R,1",
//		       "1,b=>B,L,2",
//		       "2,a=>a,L,3",
//		       "2,B=>B,L,2",
//		       "2,A=>A,R,4",
//		       "3,a=>a,L,3",
//		       "3,A=>A,R,0",
//		       "4,B=>B,R,4",
//		       "4,#=>#,R,5"};
//
//
//		TuringMachine tm = TuringMachineFactory.createTM(args);
//		assertEquals(false, tm.processData("aaabb"));
//		assertEquals(false, tm.processData("bbaa"));
//		assertEquals(true, tm.processData("aabbb"));
//		assertEquals(true, tm.processData("ab"));
//		assertEquals(true, tm.processData("aabb"));
//		assertEquals(true, tm.processData("aaaabbbb"));
//	}
	
//	@Test
//	public void testLanguage1(){
//		String[] args  =
//		      {"0,a=>a,R,0",
//			   "0,b=>b,R,1",		       
//			   "1,a=>a,R,0",
//			   "1,b=>b,R,2",		       
//		       "2,a=>a,R,0",
//		       "2,b=>b,R,3",		       
//		       "3,a=>a,R,0",
//		       "3,b=>b,R,4",
//		       "3,#=>#,R,4"};
//
//
//		TuringMachine tm = TuringMachineFactory.createTM(args);
//		assertEquals(false, tm.processData("abba"));
//		assertEquals(true, tm.processData("abbba"));
//		assertEquals(false, tm.processData("bbababb"));
//		assertEquals(true, tm.processData("babbbb"));
//		assertEquals(true, tm.processData("aaabbb"));		
//	}
	
	@Test
	public void testLanguage2(){
		
		String[] args  =
		      {"0,b=>B,R,1",
		       "1,b=>b,R,1",
		       "1,A=>A,R,1",
		       "1,a=>A,L,2",
		       "2,b=>b,L,3",
		       "2,A=>A,L,2",
		       "2,B=>B,R,4",
		       "3,b=>b,L,3",
		       "3,B=>B,R,0",
		       "4,A=>A,R,4",
		       "4,a=>a,R,5",
		       "5,#=>#,R,6"};

		TuringMachine tm = TuringMachineFactory.createTM(args);
		
		assertEquals(true, tm.processData("bbaa"));
		assertEquals(true, tm.processData("bbbbbaaaaaa"));
	
	}
	
}


