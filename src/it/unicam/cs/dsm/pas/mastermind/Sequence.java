/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.Arrays;

/**
 * @author loreti
 *
 */
public class Sequence {
	
	private final int[] values;
	private final int numeroSimboli;
	
	public Sequence(int numeroSimboli, int ... values) {
		this.values = values;
		this.numeroSimboli = numeroSimboli;
	}
	
	public int getValue(int i) {
		return values[i];
	}
	
	public int lenght() {
		return values.length;
	}
	
	public Hint verify(Sequence s) {
		if (lenght() != s.lenght()) {
			throw new IllegalArgumentException();
		}
		int posizioneCorretta = numeroElementiInPosizioneCorretta(s);
		int simboliInComune = numeroSimboliInComune(s);
		return new Hint(posizioneCorretta,simboliInComune-posizioneCorretta);
	}

	private int[] occorrenze() {
		int[] o = new int[numeroSimboli];
		for(int i=0; i<lenght(); i++) {
			int s = getValue(i);
			o[s] = o[s]+1;
		}
		return o;
	}
		
	private int numeroSimboliInComune(Sequence s) {
		int[] o1 = this.occorrenze();
		int[] o2 = s.occorrenze();
		int count = 0;
		for(int i=0;i<o1.length; i++) {
			count = Math.min(o1[i], o2[2]);
		}
		return count;
	}

	private int numeroElementiInPosizioneCorretta(Sequence s) {
		int count = 0;
		for(int i=0;i<lenght();i++) {
			if (getValue(i)==s.getValue(i)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sequence other = (Sequence) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sequence [values=" + Arrays.toString(values) + "]";
	}
	
	

}
