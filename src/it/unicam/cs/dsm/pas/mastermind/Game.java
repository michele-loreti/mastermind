/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.ArrayList;

/**
 * @author loreti
 *
 */
public class Game {
	
	private Rules rules;
	private int tries;
	private Sequence secret;
	private ArrayList<Sequence> listaTentativi;
	private ArrayList<Hint> listaSuggerimenti;

	public Game( Rules rules , int tries ) {
		this.rules = rules;
		this.tries = tries;
		this.secret = rules.generaSequenza();
		this.listaTentativi = new ArrayList<>();
		this.listaSuggerimenti = new ArrayList<>();
	}
	
	public boolean terminato() {		
		return listaTentativi.size()==tries;
	}
	
	public Hint provaSequenza(int[] values) throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		Sequence test = rules.getSequence(values);
		listaTentativi.add(test);
		Hint h = secret.verify(test);
		listaSuggerimenti.add(h);
		return h;
	}

	public Sequence getSequenza(int i) {
		return listaTentativi.get(i);
	}

	public Hint getSuggerimento(int i) {
		return listaSuggerimenti.get(i);
	}
	
	public int numberOfTries() {
		return listaTentativi.size();
	}
	
	public int getMaxNumberOfTries() {
		return tries;
	}
}
