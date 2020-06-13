/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loreti
 *
 */
public abstract class AbstractRisolutore implements Risolutore {

	protected Rules rules;
	protected ArrayList<Sequence> sequenze = new ArrayList<>();
	protected ArrayList<Hint> suggerimenti = new ArrayList<>();
	
	
	@Override
	public void setRules(Rules rules) {
		this.rules = rules;
		sequenze = new ArrayList<>();
		suggerimenti = new ArrayList<>();
	}

	@Override
	public void hint(Sequence seq, Hint hint) {
		this.sequenze.add(seq);
		this.suggerimenti.add(hint);
	}
	
	public List<Sequence> filtra(List<Sequence> sequenze, Sequence seq, Hint hint) {
		ArrayList<Sequence> list = new ArrayList<>();
		for (Sequence seq2 : sequenze) {
			if (seq2.verify(seq).equals(hint)) {
				list.add(seq2);
			}
		}
		return list;
	}
	
	

}
