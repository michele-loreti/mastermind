package it.unicam.cs.dsm.pas.mastermind.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unicam.cs.dsm.pas.mastermind.Hint;
import it.unicam.cs.dsm.pas.mastermind.Sequence;

class TestSequence {

	@Test
	void testCreazioneSequenza() {
		int[] values = new int[] {1,2,3,4,5};
		Sequence seq = new Sequence(values);
		for( int i=0; i<values.length;i++) {
			assertEquals(values[i],seq.getValue(i));
		}
	}
	
	@Test
	void testSequenzeUguali() {
		int[] values = new int[] {1,2,3,4,5};
		Sequence seq1 = new Sequence(values);
		Sequence seq2 = new Sequence(values);
		Hint h = seq1.verify(seq2);
		assertEquals(values.length,h.getNumeroSimboliInPosizioneCorretta());
		assertEquals(0,h.getNumeroSimboliPresentiInPosizioneErrata());
	}

	@Test
	void testSequenzeDifferenti() {
		Sequence seq1 = new Sequence(1,2,3,4);
		Sequence seq2 = new Sequence(4,3,2,1);
		Hint h = seq1.verify(seq2);
		assertEquals(0,h.getNumeroSimboliInPosizioneCorretta());
		assertEquals(4,h.getNumeroSimboliPresentiInPosizioneErrata());
	}

	@Test
	void testSequenzeDifferenti2() {
		Sequence seq1 = new Sequence(1,2,3,4);
		Sequence seq2 = new Sequence(4,2,3,1);
		Hint h = seq1.verify(seq2);
		assertEquals(2,h.getNumeroSimboliInPosizioneCorretta());
		assertEquals(2,h.getNumeroSimboliPresentiInPosizioneErrata());
	}

	@Test
	void testSequenzeDifferenti3() {
		Sequence seq1 = new Sequence(1,1,1,1);
		Sequence seq2 = new Sequence(2,2,2,1);
		Hint h = seq1.verify(seq2);
		assertEquals(1,h.getNumeroSimboliInPosizioneCorretta());
		assertEquals(0,h.getNumeroSimboliPresentiInPosizioneErrata());
	}

	@Test
	void testAddSymbol() {
		Sequence seq1 = new Sequence(1,2,3,4);
		Sequence seq2 = new Sequence(1,2,3,4,5);
		assertEquals(seq2, seq1.add(5));
	}
}
