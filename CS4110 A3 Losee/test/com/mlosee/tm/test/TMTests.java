package com.mlosee.tm.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mlosee.tm.Move;
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
		crashTape.writeAndMoveHead("a", Move.LEFT);
		crashTape.writeAndMoveHead("a", Move.LEFT);
		
		Tape tape = new Tape("aaba");		
		assertEquals("a", tape.readCurrentInput());
		
		tape.writeAndMoveHead("b", Move.RIGHT);
		assertEquals("#baba", tape.toString());		
		assertEquals("a", tape.readCurrentInput());
		tape.writeAndMoveHead("b", Move.RIGHT);
		assertEquals("#bbba", tape.toString());	
		
		tape.writeAndMoveHead("a", Move.RIGHT);
		tape.writeAndMoveHead("b", Move.RIGHT);
		tape.writeAndMoveHead("a", Move.RIGHT);
		tape.writeAndMoveHead("a", Move.LEFT);
		
		tape.writeAndMoveHead("a", Move.RIGHT);
		tape.writeAndMoveHead("a", Move.RIGHT);
		tape.writeAndMoveHead("a", Move.RIGHT);
		tape.writeAndMoveHead("b", Move.RIGHT);
		tape.writeAndMoveHead("b", Move.RIGHT);
		assertEquals("#bbababb", tape.toString());	
	}

	@Test
	public void testGetByValue() {
		assertEquals(Move.getByValue("L").value, -1);
		assertEquals(Move.getByValue("R").value, 1);
	}
	
	@Test(expected=EdgeNotFoundException.class)
	public void testEdge() throws EdgeNotFoundException {
		State state0 = new State("0");
		State state1 = new State("1");
		
		
		state0.putEdge("a", "b", Move.RIGHT, state1);
		state0.putEdge("a", "b", Move.RIGHT, state1);
		
		assertEquals("exception!!!", state0.getEdge("zzz").getInput());
		assertEquals("a", state0.getEdge("a").getInput());
		assertEquals("b", state0.getEdge("a").getOutput());
		assertEquals(state1, state0.getEdge("a").getNextState());
		assertEquals(state1.isHaltState(), true);	
	}
	
	@Test
	public void testFactory(){
		String[] args =
		      {"0,a=>a,R,1",
			       "0,b=>b,R,1",
			       "1,b=>b,R,2",
			       "2,a=>a,R,2",
			       "2,b=>b,R,2",
			       "2,#=>#,R,H"};

		TuringMachine tm = TuringMachineFactory.createTM(args);
		assertFalse(tm.processData("ba"));
		assertFalse(tm.processData("baa"));
		assertFalse(tm.processData("bab"));
		assertTrue(tm.processData("bbbb"));
		assertTrue(tm.processData("abaaaaaaaaaaaaaaaaa"));
	}

	@Test
	public void testFactory2(){
		String[] args  =
		      {"0,a=>A,R,1",
		       "1,a=>a,R,1",
		       "1,B=>B,R,1",
		       "1,b=>B,L,2",
		       "2,a=>a,L,3",
		       "2,B=>B,L,2",
		       "2,A=>A,R,4",
		       "3,a=>a,L,3",
		       "3,A=>A,R,0",
		       "4,B=>B,R,4",
		       "4,#=>#,R,H"};

		TuringMachine tm = TuringMachineFactory.createTM(args);
		assertFalse(tm.processData("aaabb"));
		assertFalse(tm.processData("bbaa"));
		assertFalse(tm.processData("aabbb"));
		assertTrue(tm.processData("ab"));
		assertTrue(tm.processData("aabb"));
		assertTrue(tm.processData("aaaabbbb"));
	}
	
	@Test
	public void testLanguage1(){
		String[] args  =
		      {"0,a=>a,R,0",
			   "0,b=>b,R,1",		       
			   "1,a=>a,R,0",
			   "1,b=>b,R,2",		       
		       "2,a=>a,R,0",
		       "2,b=>b,R,H"};

		TuringMachine tm = TuringMachineFactory.createTM(args);
		assertFalse(tm.processData("abba"));
		assertTrue(tm.processData("abbba"));
		assertFalse(tm.processData("bbababb"));
		assertTrue(tm.processData("babbbb"));
		assertTrue(tm.processData("aaabbbXX"));		
	}
	
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
		       "4,a=>A,R,H"};

		TuringMachine tm = TuringMachineFactory.createTM(args);
		
		assertTrue(tm.processData("baa"));
		assertFalse(tm.processData("bbbaa"));
		assertTrue(tm.processData("bbaaaa"));
		assertFalse(tm.processData("bbaabb"));
		assertTrue(tm.processData("bbaaa"));
	}
	
	@Test
	public void testLanguage3(){
		
		String[] args  =
		      {"0,x=>X,R,1",
			   "0,y=>Y,R,4",
			   "4,y=>y,R,4",
			   "4,x=>x,R,4",
			   "4,X=>X,R,4",
			   "4,Y=>Y,R,4",
			   "4,#=>#,L,5",
			   "5,X=>X,L,5",
			   "5,Y=>Y,L,5",
			   "5,y=>Y,L,3",
			   "5,x=>X,R,6",
			   "5,#=>#,R,H",
			   "1,x=>x,R,1",
			   "1,X=>X,R,1",
			   "1,y=>y,R,1",
			   "1,Y=>Y,R,1",
			   "1,#=>#,L,2",
			   "2,X=>X,L,2",
			   "2,Y=>Y,L,2",
			   "2,x=>X,L,3",
			   "2,y=>y,R,6",
			   "2,#=>#,R,H",
			   "3,x=>x,L,3",
			   "3,y=>y,L,3",
			   "3,X=>X,R,0",
			   "3,Y=>Y,R,0",
			   "3,#=>#,R,H",
			   "6,X=>X,R,H",
			   "6,Y=>Y,R,H",
		       };
		
		TuringMachine tm = TuringMachineFactory.createTM(args);
		
		assertTrue(tm.processData("xyx"));
		assertFalse(tm.processData("yxxy"));
		assertTrue(tm.processData("xxyxx"));
		assertFalse(tm.processData("xyyxy"));
		assertTrue(tm.processData("yxyxyxy"));
	}
	
	@Test
	public void testLanguage4(){
		
				
			String[] args  =
			      {"0,q=>Q,R,1",
			       "1,q=>q,R,1",
			       "1,P=>P,R,1",
			       "1,p=>P,R,4",
			       "2,p=>p,L,2",
			       "2,q=>q,L,3",
			       "2,P=>P,L,2",
			       "2,Q=>Q,R,H",
			       "3,q=>q,L,3",
			       "3,Q=>Q,R,0",
			       "4,p=>p,R,4",
			       "4,Q=>Q,R,4",
			       "4,q=>Q,L,5",
			       "5,Q=>Q,L,5",
			       "5,p=>p,L,2",
			       "5,P=>P,L,2"};

		TuringMachine tm = TuringMachineFactory.createTM(args);
		
		assertTrue(tm.processData("qpq"));
		assertFalse(tm.processData("qqpqq"));
		assertFalse(tm.processData("qqppq"));
		assertTrue(tm.processData("qqppqq"));
		assertTrue(tm.processData("qqppqqq"));
		
	}
	
}


