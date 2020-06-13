/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.Set;

/**
 * @author loreti
 *
 */
public class Rules {
	
	private final int numberOfSymbols;
	private final int length;
	private final boolean withMultiplicity;

	public Rules(int numberOfSymbols, int length, boolean withMultiplicity) {
		super();
		this.numberOfSymbols = numberOfSymbols;
		this.length = length;
		this.withMultiplicity = withMultiplicity;
	}

	public int getNumberOfSymbols() {
		return numberOfSymbols;
	}

	public int getLength() {
		return length;
	}

	public boolean isWithMultiplicity() {
		return withMultiplicity;
	}
	
	public Sequence getSequence(int ... values) throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		if (values.length != this.length) {
			throw new EccezionePerSequenzaIllegale("Numero di elementi nella sequenza errato! Aspettati "+this.length+" sono "+values.length+"!");
		}
		checkContent(values);
		if (!withMultiplicity) {
			checkSingleMultiplicity(values);
		}
		return new Sequence(values);
	}

	private void checkSingleMultiplicity(int[] values) throws EccezionePerSimboloDuplicato {
		boolean[] symbols = new boolean[this.numberOfSymbols];
		for(int v: values) {
			if (symbols[v]) {
				throw new EccezionePerSimboloDuplicato(v);
			}
			symbols[v] = true;
		}
	}

	private void checkContent(int[] values) throws EccezionePerSimboloIllegale {
		for (int i=0;i<values.length;i++) {
			if ((values[i]<0)||(values[i]>=this.numberOfSymbols)) {
				throw new EccezionePerSimboloIllegale("Uso di un indice di simbolo illegale: "+values[i]);
			}
		}
	}

}
